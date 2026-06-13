package com.tgsbhadohi.TGS.classes;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tgsbhadohi.TGS.entities.fees.Fees;
import com.tgsbhadohi.TGS.entities.notifications.NotificationMessage;

@Component
public class NotificationHelper {

	public NotificationMessage feesNotificationData(Fees fees) {
			  	NotificationMessage notification = new NotificationMessage();
			  	notification.setStudentName(fees.getStudentName());
			  	notification.setStandard(fees.getClassName());
			  	notification.setRegistrationNo(fees.getRegistrationNo());
			  	notification.setNotificationType(Constants.FEES_NOTIFICATION);
			  	notification.setNotificationTitle("Fee Payment Received");
			  	notification.setNotificationMessage( "Fees of ₹" + fees.getAmount() + " have been received successfully. \nSpecial thanks for your valuable support and timely payment.");
			  	notification.setCreatedAt( LocalDateTime.now());
			  	notification.setAcademicYearCode(fees.getAcademicYearCode());
			  	Map<String, Object> extraData = new HashMap<>();

			  	if (fees.getPaymentMode() != null && !fees.getPaymentMode().trim().isEmpty()) {
			  	    extraData.put("paymentMode", fees.getPaymentMode());
			  	}
			  	if (fees.getReceiptNo() != null && !fees.getReceiptNo().trim().isEmpty()) {
			  	    extraData.put("receiptNo", fees.getReceiptNo());
			  	}
			  	if (fees.getUpdatedBy() != null && !fees.getUpdatedBy().trim().isEmpty()) {
			  	    extraData.put("updatedBy", fees.getUpdatedBy());
			  	}
			  	if (fees.getPaymentReceivedBy() != null &&  !fees.getPaymentReceivedBy().trim().isEmpty()) {
			  	    extraData.put("paymentReceivedBy",fees.getPaymentReceivedBy());
			  	}
			  	if (fees.getAmountInWords() != null && !fees.getAmountInWords().trim().isEmpty()) {
			  	    extraData.put("amountInWord",fees.getAmountInWords());
			  	}
			  	if (fees.getMonth() != null && !fees.getMonth().trim().isEmpty()) {
			  	    extraData.put("month", fees.getMonth());
			  	}
			  	notification.setExtraData(extraData);
			  	return notification;

    }
}
