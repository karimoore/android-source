package com.bloc.classes;

class Dog {
    // The length of hair which
    final float HAIR_CUT_LENGTH = 0.15f;
    // Minimum weight that any Dog can be
    final float MIN_WEIGHT = 1.25f;
	// Amount of weight to gain after eating
	final float WEIGHT_GAIN = 0.25f;
	// Amount of weight to lose after playing
	final float WEIGHT_LOSS = 0.2f;
	// Hair length
	float mHairLength;
	// Gender, either "male" or "female"
	String mGender;
	// Size, either "tiny", "small", "average", or "large"
	String mSize;
//        final String[] SIZE_ARRAY_OF_STRINGS= {"tiny", "small", "average", "large"};
//        int mSizeIndex;
             
	// Its age
	int mAge;
	// Its weight in pounds
	float mWeight;
	// The color of its coat
	String mColor;
        

	/************************************************
	 * ADD MEMBER VARIABLES HERE IF NECESSARY	
	/************************************************/
        int mMealCount; // keep track of how many meals the dog has
        int mPlayCount; // keep track of how many times dog plays
        static final int PLAY_COUNT_FOR_WEIGHT_LOSS = 6;
        static final int FEED_COUNT_FOR_WEIGHT_GAIN = 3;
        final String[] SIZES = {"tiny", "small", "average", "large"};
        int mSizeIndex; //keeps track of dogs index of SIZES
        final int MIN_INDEX_SIZE = 0;
        final int MAX_INDEX_SIZE = SIZES.length -1;
        /*
	 * getHairLength
	 *
	 * @return this Dog's hair length (float)
	 */
	/************************************************
 	 *	ASSIGNMENT:
 	 *	Create the getHairLength method
	/************************************************/
        float getHairLength(){
            return mHairLength;
        }
	/*
	 * setHairLength
	 *
	 * Sets the length of the Dog's hair (float)
	 *
	 * @param hairLength the new length of the hair (float)
	 * @return nothing
	 */
	/************************************************
 	 *	ASSIGNMENT:
 	 *	Create the setHairLength method
	/************************************************/
        void setHairLength( float hairLength) {
            mHairLength = hairLength;
        }
	/*
	 * getGender
	 *
	 * @return this Dog's gender (String)
	 */
	// ADD YOUR METHOD HERE, NAME MUST MATCH DESCRIPTION
	/************************************************
 	 *	ASSIGNMENT:
 	 *	Create the getGender method
	/************************************************/
        String getGender(){
            return mGender;
        }
	/*
	 * setGender
	 *
	 * Sets this Dog's gender
	 *
	 * @param gender the new gender of the Dog (String)
	 * @return nothing
	 */
	/************************************************
 	 *	ASSIGNMENT:
 	 *	Create the setGender method
	/************************************************/
        void setGender( String gender){
            mGender = gender;
        }
	/*
	 * getSize
	 *
	 * @return the size of the dog (String)
	 */
	/************************************************
 	 *	ASSIGNMENT:
 	 *	Create the getSize method
	/************************************************/
        String getSize(){
            return mSize;
        }
	/*
	 * setSize
	 *
	 * Sets the size of the Dog
	 *
	 * @param size the new size of the Dog (String)
	 * @return nothing
	 */
	/************************************************
 	 *	ASSIGNMENT:
 	 *	Create the setSize method
	/************************************************/
        void setSize (String size){
            mSize = size;
            switch (mSize){
                case "tiny":
                    mSizeIndex = 0;
                    break;
                case "small":
                    mSizeIndex = 1;
                    break;
                case "average":
                    mSizeIndex = 2;
                    break;
                case "large":
                    mSizeIndex = 3;
                    break;
                default:
                    System.out.println("Not a size");
            }
        }
	/*
	 * getAge
	 *
	 * @return this Dog's age (int)
	 */
	/************************************************
 	 *	ASSIGNMENT:
 	 *	Create the getAge method
	/************************************************/
        int getAge (){
            return mAge;
        }
	/*
	 * setAge
	 *
	 * Sets the age of the Dog
	 *
	 * @param age the new age of the Dog (int)
	 * @return nothing
	 */
	/************************************************
 	 *	ASSIGNMENT:
 	 *	Create the setAge method
	/************************************************/
        void setAge(int age){
            mAge = age;
        }
	/*
	 * getWeight
	 *
	 * @return this Dog's weight (float)
	 */
	/************************************************
 	 *	ASSIGNMENT:
 	 *	Create the getWeight method
	/************************************************/
        float getWeight(){
            return mWeight;
        }
	/*
	 * setWeight
	 *
	 * Sets the weight of the Dog
	 *
	 * @param weight the new weight of the Dog (float)
	 * @return nothing
	 */
	/************************************************
 	 *	ASSIGNMENT:
 	 *	Create the setWeight method
	/************************************************/
        void setWeight(float weight){
            mWeight = weight;
        }
	/*
	 * getColor
	 *
	 * @return this Dog's color (String)
	 */
	/************************************************
 	 *	ASSIGNMENT:
 	 *	Create the getColor method
	/************************************************/
        String getColor(){
            return mColor;
        }
	/*
	 * setColor
	 *
	 * Sets the color of the Dog
	 *
	 * @param color the new color of the Dog's coat (String)
	 * @return nothing
	 */
	/************************************************
 	 *	ASSIGNMENT:
 	 *	Create the setColor method
	/************************************************/
        void setColor(String color){
            mColor = color;
        }
	/*
	 * feed
	 *
	 * Feeds the Dog.
	 *
	 * Side-effect: 1. The Dog gains weight, specifically WEIGHT_GAIN
	 *              2. Every 3 meals, the Dog grows to a larger size, if
	 *                 possible
	 *                 i.e. "tiny" (3 meals later ->) "small" (3 meals later ->)
	 *                 "average" (3 meals later ->) "large"
	 *				   the Dog cannot exceed the "large" size or shrink smaller than
	 *				   "tiny"
	 * @return nothing
	 */
	/************************************************
 	 *	ASSIGNMENT:
 	 *	Create the feed method
	/************************************************/
        void feed(){
            // add on weight
           setWeight(getWeight() + WEIGHT_GAIN); 
           //increase meal count and see if dog is up a size
            if (++mMealCount == FEED_COUNT_FOR_WEIGHT_GAIN){
                System.out.println("had 3 meals, msizeIndex = " + mSizeIndex);
                mMealCount = 0;
                if (mSizeIndex < MAX_INDEX_SIZE){
                    //make sure not already biggest
                    setSize(SIZES[++mSizeIndex]);
                    System.out.println("mIndexSize: and array value" + mSizeIndex +" " + SIZES[mSizeIndex]);
                }
            }
        }
        
	/*
	 * play
	 *
	 * Let the Dog play.
	 *
	 * Side-effect: 1. The Dog loses weight, specifically WEIGHT_LOSS
	 *				2. Every 6 play invocations, the Dog shrinks to a smaller 
	 *		   		   size, if possible i.e. "large" (6 plays later->) "average" (6 plays later->) 
	 *		   		   "small" -> "tiny"
	 *		   		3. The Dog cannot shrink to a weight smaller than
	 *		  		   MIN_WEIGHT
	 * @return nothing
	 */
	/************************************************
 	 *	ASSIGNMENT:
 	 *	Create the play method
	/************************************************/
        void play(){
            //decrease weight
            float weight = getWeight();
            if (weight > MIN_WEIGHT + WEIGHT_LOSS)
                setWeight(weight - WEIGHT_LOSS);
            // increase play count and see if dog shrunk
            if (++mPlayCount >= PLAY_COUNT_FOR_WEIGHT_LOSS ){
                mPlayCount = 0;
                if (mSizeIndex > MIN_INDEX_SIZE)  //make sure not already smallest "tiny"
                    setSize(SIZES[--mSizeIndex]);
                    
                
            }
        }
	/*
	 * cutHair
	 *
	 * Cut the Dog's hair.
	 *
	 * Side-effect: the Dog's hair length is reduced by HAIR_CUT_LENGTH
	 * 				The Dog's hair cannot be shorter than 0f
	 *
	 * @return nothing
	 */
	/************************************************
 	 *	ASSIGNMENT:
 	 *	Create the cutHair method
	/************************************************/
        void cutHair() {
            //reduce hair length
            setHairLength( getHairLength() - HAIR_CUT_LENGTH);  //need check
        }
}
