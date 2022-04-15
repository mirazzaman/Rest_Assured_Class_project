package com.test;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.vimalselvam.cucumber.listener.Reporter;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "/Users/khosruzzaman/FrameWork/SwaggerBDD/src/main/java/com/features/videogame.feature", 
glue = {"com.stepDefinition"}, //Step definition file path
plugin= {"pretty", "html:OutPut_Result", }, // generating Report
tags = "not @skip",
dryRun = true, //Check Un Defined Steps from features file and test will not execute until status is true 
monochrome = true, //Console output formate
strict =true //test will execute but if ther any undefined feature left then will occured an exception like "PendingException"

)


public class TestRunner {
	

}
