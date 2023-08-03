package com.tech.sprj09.service;

import org.springframework.ui.Model;

import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.vopage.SearchVO;

/*순서 5번째*/
public interface BServiceInter {
			public void execute(Model model, IDao dao);
}
