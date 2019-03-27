package org.yearsnow.cloud.service.user;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class UserApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void StreamTest(){
        List<User> users = new ArrayList<>();

        for(int i=0;i<5;i++){
            users.add(new User("a"+i,i,i%2));
        }
        Map<String,User> userMap = users.stream().collect(Collectors.toMap(User::getName, Function.identity()));
        System.out.println(JSON.toJSONString(userMap));
    }

    @Test
    public void dealLockTest(){
        User user = new User("tom",10,1);
        Student student = new Student(2);
        new Thread(()->{
            user.addAge(5);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            student.addLevel(3);
        }).start();

        new Thread(()->{
            student.addLevel(3);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            user.addAge(5);
        }).start();
    }

    private class User{

        private String name;
        private int age;
        private int sex;

        public User(){}

        public User(String name,int age,int sex){
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        public synchronized void addAge(int add){
            this.age = this.age+add;
            System.out.println("add user age :"+this.age);
        }

        @Transactional(isolation = Isolation.READ_COMMITTED)
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }
    }

    private class Student{
        private int level;

        public Student(int level){
            this.level = level;
        }

        public synchronized void addLevel(int add){
            this.level = this.level+add;
            System.out.println("add student level :"+this.level);
        }
    }
}
