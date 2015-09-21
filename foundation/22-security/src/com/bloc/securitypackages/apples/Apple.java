package com.bloc.securitypackages;

/************************************************
 *	YOU MAY MODIFY THIS FILE AND/OR ITS LOCATION
/************************************************/

abstract class Apple extends Fruit {
	Apple(String name, int calories, Color color, double weight) {
		super(name, calories, color, weight);
        }
    

	abstract void bite();

}