package com.mhgjoker.education;

import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphJpaRepositoryFactoryBean;
import com.mhgjoker.education.security.enums.RoleType;
import com.mhgjoker.education.system.entity.RoleEntity;
import com.mhgjoker.education.system.entity.UserEntity;
import com.mhgjoker.education.system.repository.RoleRepository;
import com.mhgjoker.education.system.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = EntityGraphJpaRepositoryFactoryBean.class)
public class EducationApplication implements CommandLineRunner {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(EducationApplication.class, args);
	}

	@Override
	@Transactional
	public void run(java.lang.String... args) {
//		for (RoleType roleType : RoleType.values()) {
//			roleRepository.save(new RoleEntity(roleType.getName(), roleType.getDescription()));
//		}
//
//		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//		UserEntity user = UserEntity.builder()
//				.enabled(true)
//				.username("admin")
//				.email("m1503@gmail.com")
//				.password(bCryptPasswordEncoder.encode("12345"))
//				.build();
//
//		RoleEntity role = roleRepository.findByName(RoleType.ADMIN.getName()).get();
//		user.setRole(role);
//		userRepository.save(user);
	}
}
