package com.hiekn.plantdata;

import net.sf.json.JSONArray;
import org.junit.Test;

import java.util.Random;
public class test {

    @Test
    public static void main(String args[]){
        String str= "[\"111\"]";
        JSONArray jValue = JSONArray.fromObject(str);
        for (int i =0;i<=jValue.size();i++)
        System.out.print(jValue.getString(i));
    }
//    public void main(){
////        long[] ages={1,2,3,4,5};
////        System.out.print("[");
////        long[] longs = new long[ages.length];
////        for (int i = 0; i < ages.length-1; i++) {
////            System.out.print(ages[i]+",");
////            longs[i] = ages[i];
////        }
//
//
//                Random rand = new Random();
//                for(int i=0; i<5; i++) {
//                    System.out.print(rand.nextInt(10)+",");
//                }
//    }
}
