package io.dave.service.impl;

import java.util.List;
import java.util.Map;

import io.dave.constants.UserRoles;
import io.dave.exception.DoctorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.dave.entity.Doctor;
import io.dave.entity.User;
import io.dave.repo.DoctorRepository;
import io.dave.service.IDoctorService;
import io.dave.service.IUserService;
import io.dave.util.MyCollectionsUtil;
import io.dave.util.MyMailUtil;
import io.dave.util.UserUtil;

@Service
public class DoctorServiceImpl implements IDoctorService {

	@Autowired
	private DoctorRepository repo;

	@Autowired
	private IUserService userService;

	@Autowired
	private UserUtil util;
	
	@Autowired
	private MyMailUtil mailUtil ;

	@Override
	public Long saveDoctor(Doctor doc) {
		Long id = repo.save(doc).getId();
		if(id!=null) {
			String pwd = util.genPwd();
			User user = new User();
			user.setDisplayName(doc.getFirstName()+" "+doc.getLastName());
			user.setUsername(doc.getEmail());
			user.setPassword(pwd);
			user.setRole(UserRoles.DOCTOR.name());
			Long genId  = userService.saveUser(user);
			if(genId!=null)
				new Thread(new Runnable() {
					public void run() {
						String text = "Your uname is " + doc.getEmail() +", password is "+ pwd;
						mailUtil.send(doc.getEmail(), "DOCTOR ADDED", text);
					}
				}).start();
		}
		return id;
	}

	@Override
	public List<Doctor> getAllDoctors() {
		return repo.findAll();
	}

	@Override
	public void removeDoctor(Long id) {
		repo.delete(getOneDoctor(id));
	}

	@Override
	public Doctor getOneDoctor(Long id) {
		return repo.findById(id).orElseThrow(
				()->new DoctorNotFoundException(id+", not exist")
				);
	}

	@Override
	public void updateDoctor(Doctor doc) {
		if(repo.existsById(doc.getId()))
			repo.save(doc);
		else 
			throw new DoctorNotFoundException(doc.getId()+", not exist"); 
	}

	@Override
	public Map<Long, String> getDoctorIdAndNames() {
		List<Object[]> list = repo.getDoctorIdAndNames();
		return MyCollectionsUtil.convertToMapIndex(list);
	}
	
	@Override
	public List<Doctor> findDoctorBySpecName(Long specId) {
		return repo.findDoctorBySpecName(specId);
	}
	
	@Override
	public long getDoctorCount() {
		return repo.count();
	}

}
