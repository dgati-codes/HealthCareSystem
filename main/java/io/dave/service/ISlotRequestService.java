package io.dave.service;

import java.util.List;

import io.dave.entity.SlotRequest;

public interface ISlotRequestService {
	//patient can book slot
	Long saveSlotRequest(SlotRequest sr);
	//fetch one
	SlotRequest getOneSlotRequest(Long id);
	
	//ADMIN can view all slots
	List<SlotRequest> getAllSlotRequests();
	//ADMIN/patient can update status
	void updateSlotRequestStatus(Long id,String status);
	//PATIENT can see his slots
	List<SlotRequest> viewSlotsByPatientMail(String patientMail);
	
	//DOCTOR can see his slots
	List<SlotRequest> viewSlotsByDoctorMail(String doctorMail);
	
	List<Object[]> getSlotsStatusAndCount();
}
