package com.bloc.singletons;
import java.util.*;
import java.lang.*;

/************************************************
 *	ASSIGNMENT:
 *	Populate this class with the defined methods.
 *
 *	This is a singleton class which maintains communication
 *	between Talker and Listener interface objects.
/************************************************/

public class Speakerphone extends Object {
        //single speakerphone
        private static Speakerphone sSpeakerphone;
        //list of Listeners
        private ArrayList<Listener> mListeners = new ArrayList<Listener>(); 
        
        /*private Speakerphone (){
            sSpeakerphone = new Speakerphone();
          
        }*/
	/*
	 * get
	 *
	 * @return the singleton instance of Speakerphone (Speakerphone)
	 */
	/************************************************
	 *	ASSIGNMENT:
	 *	Implement the get method
	/************************************************/
        public static Speakerphone get() {
            if (sSpeakerphone == null) {
                sSpeakerphone = new Speakerphone();
            }
            return sSpeakerphone;
        }

	/*
	 * addListener
	 *
	 * Add a listener to Speakerphone's list
	 *
	 * @param listener an instance of the Listener interface (Listener)
	 * @return nothing
	 */
	/************************************************
	 *	ASSIGNMENT:
	 *	Implement the addListener method
	/************************************************/
        public void addListener(Listener listener){
            mListeners.add(listener);
        }
	/*
	 * removeListener
	 *
	 * Remove a Listener from the Speakerphone's list
	 *
	 * @param listener the Listener to remove (Listener)
	 * @return nothing
	 */
	/************************************************
	 *	ASSIGNMENT:
	 *	Implement the removeListener method
	/************************************************/
        public void removeListener(Listener listener){
            mListeners.remove(listener);
        }

	/*
	 * removeAll
	 *
	 * Removes all Listeners from Speakerphone
	 *
	 * @return nothing
	 */
	/************************************************
	 *	ASSIGNMENT:
	 *	Implement the removeAll method
	/************************************************/
        public void removeAll(){
            mListeners.clear();
        }
	/*
	 * contains
	 *
	 * Checks whether a Listener is found in the Speakerphone's
	 * collection.
	 *
	 * @param listener
	 * @return `true` if the Listener has already been added to
	 *		   the Speakerphone, `false` otherwise (boolean)
	 */
	/************************************************
	 *	ASSIGNMENT:
	 *	Implement the contains method
	/************************************************/	
        public boolean contains(Listener listener){
            return mListeners.contains(listener);
        }
	/*
	 * shoutMessage
	 *
	 * Sends the message to all of the Listeners tracked by Speakerphone
	 *
	 * @param talker a Talker whose message will be sent (Talker)
	 * @return nothing
	 */
	/************************************************
	 *	ASSIGNMENT:
	 *	Implement the shoutMessage method
	/************************************************/
        public void shoutMessage(Talker talker){
            //Iterate through listeners to receive message
            ListIterator<Listener> listenIterator = mListeners.listIterator();
            while (listenIterator.hasNext()){
                listenIterator.next().onMessageReceived(talker.getMessage());
            }
            
        }
	/*
	 * shoutMessage
	 *
	 * Sends the message to all of the Listeners which are instances of
	 * the cls parameter
	 *
	 * @param talker a Talker whose message will be sent (Talker)
	 * @param cls a Class object representing the type which the Listener
	 *			  should extend from in order to receive the message (Class)
	 * @return nothing
	 *
	 * HINT: see Class.isAssignableFrom()
	 *		 http://docs.oracle.com/javase/7/docs/api/java/lang/Class.html#isAssignableFrom(java.lang.Class)
	 */
	/************************************************
	 *	ASSIGNMENT:
	 *	Implement the shoutMessage method
	/************************************************/
        public void shoutMessage(Talker talker, Class cls){
            //Iterate through listeners to receive message (if implements Listener)
            ListIterator<Listener> listenIterator = mListeners.listIterator();
            while (listenIterator.hasNext()){
                if (Listener.class.isAssignableFrom(cls)){
                        listenIterator.next().onMessageReceived(talker.getMessage());
                }
            }
            
        }
}