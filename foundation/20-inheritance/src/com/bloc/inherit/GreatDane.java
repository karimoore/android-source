package com.bloc.inherit;

/************************************************
 *	ASSIGNMENT:
 *	Define the GreatDane class below
 *
 *	Great Danes have an extra size category, "huge".
 *	After growing to a "large" size, they may grow
 *	to an additional, "huge" size after 3 meals.
/************************************************/
class GreatDane extends Dog {
    final static int SIZE_HUGE_4 = 4;
    GreatDane(){
        setSizeIndex(SIZE_HUGE_4); //account for the extra size of great dane
    }


	/*
	 * subclass getSizeIndex
         * @description account for added great dane size: "huge"
	 * @param size the string value representing the size
	 * @return an index between 0 and 4 in the
	 * 		   array of {"tiny", "small", "average", "large", "huge"}
	 */
         @Override
	int getSizeIndex(String size) {
		if (size == null)
			return SIZE_AVERAGE_2;  //return average
                else if( "huge".equals(size) )
			return SIZE_HUGE_4;
                else 
                    return super.getSizeIndex(size);
	}

	/*
	 * subclass fromSizeIndex
	 * @param index the index into the sizes array
	 * @return a String, one of {"tiny", "small", "average", "large"}
	 */
        @Override
	String fromSizeIndex(int index) {
            if (index == SIZE_HUGE_4)
                return "huge";
            else 
                return super.fromSizeIndex(index);
	}
        
}