package com.bloc.bestpractices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WorstPractices extends Object {

    /************************************************
     *	ASSIGNMENT:
     *	Fix code and comments below this block
    /************************************************/
    public static final int CONST_NUMBER = 5;
    public static final int CONST_COUNT = 18;
        
    public static void main(String [] args) {
        int magicNumber = WorstPractices.animalEquation(false);
        magicNumber *= CONST_NUMBER;
        if (magicNumber > CONST_COUNT) {
            while(magicNumber > 0) {
                magicNumber--;
            }
        }
    }

    /***************************************
     *   Class:         WorstPractice
     *   Method:        animalEquation
     *   Param:         isAnimal
     *   Return:        returnNumber 
     *   Description:   If isAnimal TRUE return the number as a 
     *                  result of using ANIMAL_NUMBER, Otherwise 
     *                  return the solution using NOT_AN_ANIMAL_NUMBER
     *****************************************/
    private static int animalEquation(boolean isAnimal) {
        final int ANIMAL_NUMBER = 34;
        final int NOT_AN_ANIMAL_NUMBER = 21;
        float returnNumber = 0.5f;

        int animalNumber = isAnimal ? ANIMAL_NUMBER : NOT_AN_ANIMAL_NUMBER;
        for (int i = 0; i <= animalNumber; i++) { 
            returnNumber *= animalNumber;
        } 
        return (int) returnNumber;
    }

	/************************************************
	 *	ASSIGNMENT
	 *	Fix code and comments above this block
	/************************************************/
}
