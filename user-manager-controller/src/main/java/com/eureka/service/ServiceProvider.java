package com.eureka.service;

import com.eureka.dto.Response;
import com.eureka.service.IService.IServiceProvider;
import com.eureka.vo.StudentVO;
import com.eureka.vo.TeacherVO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceProvider implements IServiceProvider {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	@HystrixCommand(fallbackMethod = "Error")
	public Response registerStudent() {
		StudentVO studentVO = new StudentVO();
		return restTemplate.postForObject("http://SERVICE-RIBBON/registerStudent", studentVO, Response.class);
	}

	@Override
	@HystrixCommand(fallbackMethod = "Error")
	public Response registerTeacher() {
		TeacherVO teacherVO = new TeacherVO();
		return restTemplate.postForObject("http://SERVICE-RIBBON/registerTeacher", teacherVO, Response.class);
	}

	@Override
	@HystrixCommand(fallbackMethod = "Error")
	public Response login() {
		TeacherVO teacherVO = new TeacherVO();
		return restTemplate.postForObject("http://SERVICE-RIBBON/login", teacherVO, Response.class);
	}

	public Response Error() {
		return Response.MICRO_SERVICE_UNUSE;
	}
}
