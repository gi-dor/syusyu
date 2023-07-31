package com.teamProject.syusyu.dao.order;

import com.teamProject.syusyu.domain.order.PayDTO;

import java.util.Map;

public interface PayDAO {
    int insertPay(PayDTO payDTO) throws Exception;

    int insertCancelPay(Map<String, Object> param) throws Exception;

    PayDTO selectPay(int payNo) throws Exception;
    int deleteAllPay() throws Exception;
    int countPay() throws Exception;
}
