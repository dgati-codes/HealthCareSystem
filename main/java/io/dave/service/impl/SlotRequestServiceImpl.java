package io.dave.service.impl;

import java.util.List;
import java.util.Optional;

import io.dave.constants.SlotStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.dave.entity.SlotRequest;
import io.dave.repo.SlotRequestRepository;
import io.dave.service.ISlotRequestService;

@Service
public class SlotRequestServiceImpl implements ISlotRequestService {

	@Autowired
	private SlotRequestRepository repo;
	
	public Long saveSlotRequest(SlotRequest sr) {
		return repo.save(sr).getId();
	}
	
	public SlotRequest getOneSlotRequest(Long id) {
		Optional<SlotRequest> opt = repo.findById(id);
		if(opt!=null) {
			return opt.get();
		}
		return null;
	}

	public List<SlotRequest> getAllSlotRequests() {
		return repo.findAll();
	}

	@Transactional
	public void updateSlotRequestStatus(Long id, String status) {
		repo.updateSlotRequestStatus(id, status);
	}
	
	public List<SlotRequest> viewSlotsByPatientMail(String patientMail) {
		return repo.getAllPatientSlots(patientMail);
	}
	
	@Override
	public List<SlotRequest> viewSlotsByDoctorMail(String doctorMail) {
		return repo.getAllDoctorSlots(doctorMail, SlotStatus.ACCEPTED.name());
	}
	
	@Override
	public List<Object[]> getSlotsStatusAndCount() {
		return repo.getSlotsStatusAndCount();
	}

}
