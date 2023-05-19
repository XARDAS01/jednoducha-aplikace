package com.example.javatestapp;

/**
 * @author Yuri Raduntsev
 * Some comment
 * */

import com.google.zxing.WriterException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class JavaTestAppApplication {
	public static void main(String[] args) throws IOException, WriterException {
		SpringApplication.run(JavaTestAppApplication.class, args);
	}
}
