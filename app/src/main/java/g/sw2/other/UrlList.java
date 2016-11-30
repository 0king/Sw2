package g.sw2.other;

import java.util.Random;

/**
 * Created by 5dr on 22/10/16.
 */

public class UrlList {

	static String[] coverPicList = {"http://data.whicdn.com/images/91488886/large.jpg",
			"http://www.spheresoflight.org/wp-content/uploads/2012/04/Flying-High1.jpg?b9d53e",
			"http://blogs.r.ftdata.co.uk/photo-diary/files/2013/06/balloons.jpg",
			"http://www.srkinfosystems.com/uploads/admin/8243429879c4fe3ad8342a4961c84edf.jpg",
			"http://www.pickycovers.com/uploads/cover/cc864e22ed0528a54a5d1187d79f03ba.jpg",
			"http://youngandbeast.com/wp-content/uploads/2014/12/the-skys-the-limit-860x321.jpg",
			"http://belimitless.com/wp-content/uploads/2014/08/345620-admin-810x413.jpg",
			"http://www.overmundo.com.br/uploads/banco/multiplas/1258589914_flor_com_ceu_azul.jpg",
			"http://dreamatico.com/data_images/sky/sky-7.jpg",
			"http://weknowyourdreams.com/images/sky/sky-07.jpg",
			"http://weknowyourdreams.com/images/sky/sky-03.jpg",
			"https://static.pexels.com/photos/9135/sky-clouds-blue-horizon.jpg",
			"http://www.pixelstalk.net/wp-content/uploads/2016/05/Blue-sunny-sky.jpg"};
	//total 13 urls

	public static String coverPicChooser(){
		//int i = (int) (Math.random()*100)%13;
		Random r = new Random();
		int i = r.nextInt(13 - 0) + 0; //random integer between 0 (inclusive) and 13 (exclusive)
		return coverPicList[i];
	}
}
