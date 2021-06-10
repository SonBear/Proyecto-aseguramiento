package com.chsoft;

/**
 *
 * @author ajas
 */
public class Triangle {
    
   public String triangle(int a, int b, int c){ 
       
       if (a == 0 || b == 0 || c == 0){
        throw new TriangleException();
       }
       
        if (a == b && b == c){
            return TriangleType.EQUILATERAL;
        }else if(a == b || a == c || c == b){
            return TriangleType.ISOSCELES;
        }else{
            return TriangleType.SCALENE;
        }
    }
}
