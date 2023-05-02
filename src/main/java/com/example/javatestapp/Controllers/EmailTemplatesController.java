package com.example.javatestapp.Controllers;

import com.example.javatestapp.Models.AbstractEmailContext;
import com.example.javatestapp.Payloads.Requests.SendSimpleEmailRequest;
import com.example.javatestapp.Payloads.Responses.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = {"api/v1/templates"})
public class EmailTemplatesController {
    @Autowired
    private SpringTemplateEngine templateEngine;

    @GetMapping("/getTemplates")
    public MessageResponse simpleEmail () {
        try {
            Map<String, Object> map = new HashMap<>(){};
            AbstractEmailContext abstractEmailContext = new AbstractEmailContext("yuri.raduntcev@gmail.com", "testHtml", "", "", "", "", "", "welcome-email.html", map);
            Context context = new Context();
            context.setVariables(abstractEmailContext.getContext());
            String emailContent = templateEngine.process(abstractEmailContext.getTemplateLocation(), context);
            return new MessageResponse("ok", 200, emailContent);
        } catch (Exception e) {
            return new MessageResponse("some error", 400, e);
        }
    }
}
