package com.sample;

import java.util.Scanner;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");
        	Scanner scan=new Scanner(System.in);
        	String name="";
    		float score=0;
        	// go !s
        	while(true)
            {
        		System.out.print("请输入学生  分数  和  姓名  ，空格隔开，分数不在0-100将退出程序\n");
        		score=scan.nextFloat();
        		if(score<0||score>100)
        			break;
        		name=scan.next();
        		Student stu = new Student(name,score);
        		kSession.insert(stu);
        		kSession.fireAllRules();
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    public static class Student {

        private String name;
        private float score;
        public Student(String n,float s)
        {
        	name=n;
        	score=s;
        }

        public float getScore() {
            return this.score;
        }
        public String getname() {
            return this.name;
        }

        public void setname(String n) {
            this.name =n;
        }

        public void setScore(float s) {
            this.score=s;
        }
    }
}
