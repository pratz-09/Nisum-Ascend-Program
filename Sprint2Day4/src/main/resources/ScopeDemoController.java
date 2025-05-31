package com.nisum.controller;

import com.nisum.beans.SingletonBean;
import com.nisum.beans.RequestBean;
import com.nisum.beans.SessionBean;
import com.nisum.beans.PrototypeBean; // <-- updated import

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ScopeDemoController {

    @Autowired
    private SingletonBean singletonBean;

    @Autowired
    private PrototypeBean prototypeBean; // <-- updated type

    @Autowired
    private RequestBean requestBean;

    @Autowired
    private SessionBean sessionBean;

    @GetMapping("/scopes")
    public Map<String, String> getBeanIds(HttpSession httpSession) {
        Map<String, String> ids = new HashMap<>();
        ids.put("singleton", singletonBean.getId());
        ids.put("prototype", PrototypeBean.getId());
        ids.put("request", requestBean.getId());
        ids.put("session", sessionBean.getId());
        ids.put("httpSessionId", httpSession.getId());
        return ids;
    }
}