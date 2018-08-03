package service;

import dao.LocationDao;
import dao.SchoolDao;
import dao.ShelfDao;
import dao.UmbrellaDao;
import dao.UserDao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
import java.util.List;

import entity.Location;
import entity.School;
import entity.Shelf;
import entity.Umbrella;
import entity.User;

public class UmbrellaService {
	UmbrellaDao umd = new UmbrellaDao();
	public Umbrella searchById(BigDecimal id) {
		UmbrellaDao umd = new UmbrellaDao();
		return umd.select(id);
	}
	public List<School> schoolList() {
		SchoolDao sd = new SchoolDao();
		return sd.selectList();
	}
	public List<Location> locationList(int schoolId) {
		LocationDao dl = new LocationDao();
		return dl.selectList(schoolId);
	}
	public List<Location> selectListBySchoolName(String name) {
		LocationDao dl = new LocationDao();
		return dl.selectListBySchoolName(name);
	}
	public List<Shelf> shelfList(String schoolName) {
		ShelfDao sd = new ShelfDao();
		return sd.selectList(schoolName);
	}
	public int updateShelf(Shelf sh) {
		ShelfDao sd = new ShelfDao();
		return sd.update(sh);
	}
	public int insertShelf(BigDecimal loc_id) {
		ShelfDao sd = new ShelfDao();
		return sd.insert(loc_id);
	}

	/**
	 * 根据位置名和学校名查找多把伞
	 * @param locName: 位置名
	 * @param schoolName: 学校名
	 * @return: 伞的列表
	 */
	public List<Umbrella> searchByLocAndSchool(String locName, String schoolName){
		return umd.selectList(locName, schoolName);
	}
	/**
	 * 用户借伞
	 * 置伞的ubl_lent_situation为2
	 * @param u: 借伞用户
	 * @param um: 借的伞
	 * @param days: 所借天数
	 * @return: 返回所影响的行，若为-1，则出错
	 */
	public int borrow(User u, Umbrella um, int days) {
		int rows = 0;
		if (!u.getUser_id().equals(0)) {
			Umbrella getUm = umd.select(um.getUbl_id());
			if (getUm != null && getUm.getLent_situation().compareTo(new BigDecimal(1)) == 0) {
				UserService us = new UserService();
				List<Umbrella> ums = us.getOrder(u.getUser_name());
				if (ums.size() < 3) {
					um.setLent_situation(new BigDecimal(2)); // 标记为借出状态
					um.setLent_user_id(u.getUser_id());
					Date nowD = new Date(System.currentTimeMillis());
					//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					um.setLent_start_time(new Timestamp(nowD.getTime()));
					um.setLent_end_time(new Timestamp(nowD.getTime() + days * 24 * 60 * 60 * 1000));
					rows = umd.update(um);
				} else rows = -1;
			} else rows = -1;
		} else rows = -1;
		return rows;
	}
	/**
	 * 还伞
	 * 若时间不到截止时间，则按照截止时间计算金额
	 * 若当前时间超过截止时间，则按照阶梯方式计价。超过截止时间的天数按每日的1.5倍计算金额
	 * @param u: 还伞的用户
	 * @param um: 还的伞
	 * @return: 返回影响的行数，若为-1则出错
	 */
	public int giveBack(User u, Umbrella um) {
		int rows = 0;
		UserService us = new UserService();
		UserDao ud = new UserDao();
		List<Umbrella> ums = us.getOrder(u.getUser_name());
		if (!ums.isEmpty() && um.getLent_user_id().compareTo(u.getUser_id()) == 0) {
			for (Umbrella uum : ums) {
				if (uum.getUbl_id().compareTo(um.getUbl_id()) == 0) {
					User uN = ud.select(u.getUser_name());
					if (uN != null) {
						BigDecimal wallet = uN.getUser_wallet();
						Timestamp newT = new Timestamp(System.currentTimeMillis());
						BigDecimal days = new BigDecimal(newT.getTime() - um.getLent_start_time().getTime()).divide(new BigDecimal(86400000), 10, RoundingMode.CEILING);
						BigDecimal initDays = new BigDecimal(um.getLent_end_time().getTime() - um.getLent_start_time().getTime()).divide(new BigDecimal(86400000), 10, RoundingMode.CEILING);
						if (days.compareTo(initDays) == -1) {
							wallet = wallet.subtract(um.getUbl_price_per_day().multiply(initDays));
						} else {
							wallet = wallet.subtract(initDays.multiply(um.getUbl_price_per_day()).add((days.subtract(initDays)).multiply(new BigDecimal(1.5)).multiply(um.getUbl_price_per_day())));
						}
						um.setLent_situation(new BigDecimal(1));
						um.setLent_user_id(new BigDecimal(0));
						um.setLent_start_time(null);
						um.setLent_end_time(null);
						uN.setUser_wallet(wallet);
						rows = ud.update(uN);
						if (rows > 0) {
							rows = umd.updateLent(um);
						}
						else rows = -1;
					} else rows = -1;
				} else rows = -1;
			}
		} else rows = -1;
		return  rows;
	}
	/**
	 * 伞挂失
	 * 首先扣除伞的借用费，再扣除50元的伞费，再删除伞
	 * @param u: 借伞用户
	 * @param um: 借的伞
	 * @return: 返回影响行数，若为-1，则出错
	 */
	public int reportOfLoss(User u, Umbrella um) {
		int rows = 0;
		UserService us = new UserService();
		UserDao ud = new UserDao();
		List<Umbrella> ums = us.getOrder(u.getUser_name());
		if (!ums.isEmpty() && ums.contains(um)) {
			um.setLent_situation(new BigDecimal(1));
			um.setLent_user_id(new BigDecimal(-1));
			um.setLent_start_time(null);
			um.setLent_end_time(null);
			User uN = ud.select(u.getUser_name());
			if (uN != null) {
				BigDecimal wallet = uN.getUser_wallet();
				Date newD = new Date(System.currentTimeMillis());
				BigDecimal days = new BigDecimal(newD.getTime() - um.getLent_start_time().getTime()).divide(new BigDecimal(86400000), 10, RoundingMode.CEILING);
				BigDecimal initDays = new BigDecimal(um.getLent_end_time().getTime() - um.getLent_start_time().getTime()).divide(new BigDecimal(86400000), 10, RoundingMode.CEILING);
				if (days.compareTo(initDays) == -1) {
					wallet = wallet.subtract(um.getUbl_price_per_day().multiply(initDays));
				} else {
					wallet = wallet.subtract(initDays.multiply(um.getUbl_price_per_day()).add((days.subtract(initDays)).multiply(new BigDecimal(1.5)).multiply(um.getUbl_price_per_day())));
				}
				wallet = wallet.subtract(new BigDecimal(50)); // 钱包减去伞的价格，50元/把
				uN.setUser_wallet(wallet);
				rows = ud.update(uN);
				if (rows > 0) { 
					umd.delete(um);
				} else rows = -1;
			} else rows = -1;
		} else rows = -1;
		return  rows;
	}
}
