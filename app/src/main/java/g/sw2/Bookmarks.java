package g.sw2;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by 5dr on 23/02/17.
 */


	/*
		Class to store all the bookmarks created by user
	*/


public class Bookmarks{
	private String userId;

	private class Subject{
		String subjectName;
		LinkedList<Chapter> chapterList;

		Subject(String subjectName){
			this.subjectName = subjectName;
			this.chapterList= new LinkedList<>();
		}

		public boolean isChapterListEmpty(){
			return (this.chapterList.size()==0);
		}

		public LinkedList<Chapter> getChapterList() {
			return chapterList;
		}

		public void setChapterList(LinkedList<Chapter> chapterList) {
			this.chapterList = chapterList;
		}

		public String getSubjectName() {
			return subjectName;
		}

		public void setSubjectName(String subjectName) {
			this.subjectName = subjectName;
		}
	}
	private ArrayList<Subject> subjects= new ArrayList<Subject>();

	private class Chapter{
		String chapterName;
		int chapterId; //is also chapter index
		LinkedList<Topic> topicList;

		Chapter(String chapterName){//todo add chapterId
			this.chapterName=chapterName;
			this.topicList=new LinkedList<>();
		}

		public boolean isTopicListEmpty(){
			return (this.topicList.size()==0);
		}

		int getChapterId(){return this.chapterId;}

		String getChapterName(){return this.chapterName;}

		LinkedList<Topic> getTopicList(){return this.topicList;}
	}


	private class Topic{
		String topicName;
		int topicId;
		LinkedList<String> bookmarkList; //todo - bookmarkId (card id) to be created in increasing order

		Topic(String topicName, int topicId){
			this.topicId=topicId;
			this.topicName=topicName;
			this.bookmarkList=new LinkedList<>();
		}

		public LinkedList<String> getBookmarkList() {
			return bookmarkList;
		}

		public int getTopicId() {
			return topicId;
		}

		public String getTopicName() {
			return topicName;
		}
	}

	void editSubject(Subject subject){}
	void deleteSubject(Subject subject){}
	ArrayList<Subject> getSubjects(){return subjects;}


	void addBookmark(String cardId, String subjectName, String newChapterName, int newChapterId, String newTopicName,int newTopicId){

		//check if subject exists or not
		//check if chapter exists or not
		//check if card id exists or not
		//then add

		Subject newSubject = new Subject(subjectName);
		Chapter newChapter = new Chapter(newChapterName);
		Topic newTopic = new Topic(newTopicName, newTopicId);


		//add first bookmark
		if(subjects.size()==0){
			//add first subject
			subjects.add(newSubject);
			//create its linked chapter list
			newSubject.chapterList.add(newChapter);
			//create chapter's topic list
			newChapter.topicList.add(newTopic);
			//now add bookmark to topic's DLL
			newTopic.bookmarkList.add(cardId);
		}



		if(subjects.contains(newSubject)){
			//check if chapter list is empty
			if(newSubject.chapterList.isEmpty()){
				//add new chapter
				addObjectSequentially(newChapter,newSubject.chapterList);
				addObjectSequentially(newTopic,newChapter.topicList);
				addObjectSequentially(cardId,newTopic.bookmarkList);
			}
			//check if chapter is present
			else if (newSubject.chapterList.contains(newChapter)){
				//check if topic list is empty
				if (newChapter.topicList.isEmpty()){
					//add new topic
					addObjectSequentially(newTopic,newChapter.topicList);
					addObjectSequentially(cardId,newTopic.bookmarkList);
				}
				//check if topic is also already present
				else if(newChapter.topicList.contains(newTopic)){
					//check if bookmark empty
					if(newTopic.bookmarkList.isEmpty()){
						//add bookmark
						addObjectSequentially(cardId,newTopic.bookmarkList);
					}
					//check for id
					else if(newTopic.bookmarkList.contains(cardId)){
						//do nothing
					}
					else {
						//add bookmark sequentially
						addObjectSequentially(cardId,newTopic.bookmarkList);//add object in sorted order
					}
				}
				else {
					//add new topic
					addObjectSequentially(newTopic,newChapter.topicList);
					addObjectSequentially(cardId,newTopic.bookmarkList);
				}
			}
			else {
				//add new chapter
				addObjectSequentially(newChapter,newSubject.chapterList);
				addObjectSequentially(newTopic,newChapter.topicList);
				addObjectSequentially(cardId,newTopic.bookmarkList);
			}
		}
		else{
			//add new subject
			subjects.add(newSubject);
			//add first bookmark for the new subject
			addBookmark(cardId,subjectName,newChapterName,newChapterId,newTopicName,newTopicId);//calling this function recursively
		}


	}

	private <O> void addObjectSequentially(O object, LinkedList<O> list){

		int idOfObject;

		if(object instanceof Chapter)
			idOfObject = ((Chapter) object).getChapterId();
		else
			idOfObject = ((Topic) object).getTopicId();


		if (list.size()==0) {
		}
		else if(list.size()==1){
		}
		else{
			int idOfCurrentObject;//todo change int to double, generic form
			int indexOfCurrentObject;
			for (Object o : list){
				//get the id(defined by editor) and index of current object o
				indexOfCurrentObject = list.indexOf(o);
				if (object instanceof Chapter) idOfCurrentObject = ((Chapter) o).getChapterId();
				else idOfCurrentObject = ((Topic) o).getTopicId();
				//now do the comparison
				try {
					if(idOfObject<idOfCurrentObject) {
						list.add(indexOfCurrentObject-1,object);
					}
					else if(idOfObject==idOfCurrentObject) {
						//this case wont arise here, as calling function handles this case
					}
					else {
						list.addLast(object);
					}
				}
				catch (Exception e) {
					Log.e("addObjectSequentially",e.toString());
				}

			}
		}

	}

	void deleteBookmark(String id, String chapter, String topic){

	}




}
