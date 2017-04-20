package g.sw2.other;

import java.util.Random;

/**
 * Created by 5dr on 22/10/16.
 */

public class UrlList {
	
	static String[] coverPicList = {
			"https://s-media-cache-ak0.pinimg.com/736x/7c/d6/65/7cd665a063e0366ac8358cb310c9a2b2.jpg",//if it doesnot challenge you wont change you
			"https://s-media-cache-ak0.pinimg.com/736x/af/e2/b4/afe2b4a6ca3a36d05a914da9d18cff1d.jpg",//diificult raods often lead to beautiful destinations
			"https://s-media-cache-ak0.pinimg.com/736x/70/48/c2/7048c292b0ee4891b6aa473b31b9d7b8.jpg",//train your mond to see the good in every situation
			"https://www.askideas.com/wp-content/uploads/2017/01/The-difference-between-ordinary-and-extraordinary-is-PRACTICE.-V.-Horowitz.jpg",
			"https://www.askideas.com/wp-content/uploads/2017/01/When-you-are-not-practicing-remember-somewhere-someone-is-practicing-and-when-you-meet-him-he-will-win.-Ed-Macauley.png",
			"https://www.askideas.com/wp-content/uploads/2017/01/Practice-isnt-the-thing-you-do-once-youre-good.-Its-the-thing-you-do-that-makes-you-good.-Malcolm-Gladwell.png",
			"https://s-media-cache-ak0.pinimg.com/736x/33/2f/db/332fdb45515f6335b836ad921c8ec0db.jpg",//there is no glory in practice, but without it theres no glory-unknown
			"http://files.planetofsuccess.com/blog/wp-content/uploads/2015/10/stop.jpg",//dont stop when you're tired, but when done
			"http://files.planetofsuccess.com/blog/wp-content/uploads/2015/10/tough-times1.jpg",//tough times never last, but tough people
			"http://files.planetofsuccess.com/blog/wp-content/uploads/2015/10/hard-work1.jpg",//hard work beats talent
			"https://s-media-cache-ak0.pinimg.com/736x/b5/3b/0f/b53b0fb4921620c21f7c34ee4d5a4a20.jpg",//expert was a beginner
			"https://s-media-cache-ak0.pinimg.com/736x/7f/08/49/7f08499d219335b3953eb8712b77db49.jpg",//stay positive work hard make it happen
			"https://www.brainyquote.com/photos_tr/en/a/aristotle/379604/aristotle1.jpg",//quality is habit
			"https://s-media-cache-ak0.pinimg.com/originals/a4/d3/40/a4d340f895d9d32066817e81c5740ae8.jpg",//work hard dream big
			"https://s-media-cache-ak0.pinimg.com/736x/f7/2d/23/f72d23e7d51d47775b01404cb509b2d5.jpg",//push harder than yesterday for diff tomm
			"https://www.brainyquote.com/photos_tr/en/w/winstonchurchill/143691/winstonchurchill1.jpg",//never give up - churchill
			"https://s-media-cache-ak0.pinimg.com/736x/04/43/3e/04433e4b7642d0c982dd396d7143653a.jpg",//i can i will
			"https://s-media-cache-ak0.pinimg.com/736x/c8/5b/73/c85b7363306e52a9ca00b5ee9f0a80ae.jpg",//unless you die, keep going
			"https://s-media-cache-ak0.pinimg.com/originals/1c/c8/83/1cc883a64f0757d64dc2b9a5dca544a8.jpg",//stop wishing, start doing
			"https://s-media-cache-ak0.pinimg.com/736x/44/2e/e6/442ee6068b801c00376933b01afebe93.jpg",//in japan broken objects gold
			"https://s-media-cache-ak0.pinimg.com/736x/cf/14/c8/cf14c8ddd128662d902f511a122cf1dc.jpg",//you fail when you stop trying
			"https://s3.amazonaws.com/media.briantracy.com/blog/quotes/rob-siltanen-people-who-are-crazy-enough-to-change-the-world.jpg",//crazy enough to think to change the world
			"https://s-media-cache-ak0.pinimg.com/736x/db/f8/e6/dbf8e6f859df16669b4d3d302c86a486.jpg",//success is not built on success, built on failures, frustration
			"https://lh3.googleusercontent.com/r7Ld0kw_azhxUoFGqZN3AouSrsYVXMBEtPU0f4yq0UANKYdt8hEEGKyRxPBMCoTNFA=w300",//look in the mirror, thats your competition
			"https://s-media-cache-ak0.pinimg.com/736x/8a/06/d2/8a06d20324deecbcd387015664c403b7.jpg",//be afraid not to try
			"https://cdn.motivationgrid.com/wp-content/uploads/2013/12/4-Motivational-Quotes.jpg",//challenge your limits
			"https://www.brainyquote.com/photos_tr/en/c/carolburnett/371189/carolburnett1.jpg",//Only I can change my life. No one can do it for me
			"https://www.brainyquote.com/photos/n/nelsonmandela378967.jpg",//It always seems impossible until it's done.
			"https://www.brainyquote.com/photos/c/confucius140908.jpg",//It does not matter how slowly you go as long as you do not stop
			"https://s3.amazonaws.com/media.briantracy.com/blog/quotes/johann-von-goethe-knowing-is-not-enough.png",//Knowing Is Not Enough; We Must Apply. Wishing Is Not Enough; We Must Do
			"https://s3.amazonaws.com/media.briantracy.com/blog/quotes/don-zimmer-what-you-lack-in-talent.png",//“What You Lack In Talent Can Be Made Up With Desire, Hustle And Giving 110% All The Time.
			"https://s3.amazonaws.com/media.briantracy.com/blog/quotes/do-what-you-can-with-all-you-have.png",//Do What You Can With All You Have, Wherever You Are
			"http://files.planetofsuccess.com/blog/wp-content/uploads/2015/10/moon1.jpg",//Aim for the moon. If you miss, you may hit a star.
			"http://files.planetofsuccess.com/blog/wp-content/uploads/2015/10/Action.jpg",//Action is the foundational key to all success.
			"http://files.planetofsuccess.com/blog/wp-content/uploads/2015/10/luck.jpg",//I find that the harder I work, the more luck I seem to have.
			"http://files.planetofsuccess.com/blog/wp-content/uploads/2015/10/comfort.jpg",//Life begins at the end of your comfort zone.
			"http://files.planetofsuccess.com/blog/wp-content/uploads/2015/10/desire-to-succeed.jpg",//The will to win, the desire to succeed, the urge to reach your full potential… these are the keys that will unlock the door to personal excellence.
			"http://files.planetofsuccess.com/blog/wp-content/uploads/2015/10/things-turn-out.jpg",//Things work out best for those who make the best of how things work out.
			"http://files.planetofsuccess.com/blog/wp-content/uploads/2015/10/problems.jpg",//Expect problems and eat them for breakfast.
			"http://quotes.values.com/quote_artwork/7621/large/20170419_wednesday_quote_alternate_updated.jpg",//The value of life is not based on how long we live, but how much we contribute to others in our society
			"http://quotes.values.com/quote_artwork/7620/large/20170418_tuesday_quote_alternate.jpg",//To succeed in your mission, you must have single-minded devotion to your goal.
			"http://quotes.values.com/quote_artwork/6432/large/20170417_monday_quote.jpg",//I know the price of success: dedication, hard work, and an unremitting devotio...
			"http://www.success.com/sites/default/files/9_10.jpg",//There will be obstacles. There will be doubters. There will be mistakes. But with hard work, there are no limits
			"http://www.success.com/sites/default/files/14_10.jpg",//You just can’t beat the person who never gives up
			"http://cdn-media-1.lifehack.org/wp-content/files/2016/12/15081408/51.png",//work untill idols become rivals
			"http://www.fpratampabay.org/wp-content/uploads/bfi_thumb/comfort-zone-324oba0oo9mxt21czir85m.png",//if it scares you try it
			"http://www.potentash.com/wp-content/uploads/2017/02/comfort-zone.png",//comfort zone heart dream life
			"https://media.licdn.com/mpr/mpr/AAEAAQAAAAAAAAvxAAAAJDZmMzc2NDNlLWMzYWEtNGNkZS05ZmMwLTY5MDc4Yjc4NDk1MQ.jpg",//your comfort zone where magic happens
			"https://www.brainyquote.com/photos/t/thomasaedison131293.jpg",//theres no substitue for hard work
			"http://wallpapercave.com/wp/qe1NBOZ.png",//hard work pays off
			"http://www.frugalrules.com/wp-content/uploads/2016/01/is-not-luck-its-hard-work-horz-640x450.png",
			"https://thechristianrunaway.com/wp-content/uploads/2015/06/discipline-wallpaper-10305835-600x533.jpg",//discipline doing what is right even no desire
			"https://s-media-cache-ak0.pinimg.com/736x/3c/79/57/3c79570838d1ba193c5c6c7db849af0b.jpg",//focus on your goal, dont look in any direction but ahead
			"https://www.askideas.com/media/87/Focus-on-where-you-want-to-be-not-where-you-were-or-where-you-are.jpg",//focus on where you want to be, not past
			"http://www.quotemaster.org/images/56/56d55d1d8bbfcfe15596417337de1b69.jpg",//focus on really important
			"http://www.quotemaster.org/images/36/3633238a3ceffab443789ed3429042da.jpg",//if you chase 2 rabbit both will escape
			"http://www.quotehd.com/imagequotes/authors72/rig-veda-quote-the-main-factor-behind-success-is-self-control.jpg",
			"http://cdn-media-2.lifehack.org/wp-content/files/2015/07/Learning-Quotes-2-of-16.jpg",//learn as if you will live foreever
			"http://www.hippoquotes.com/img/brain-quotes/quote,inspiration,motivation,quotes,word,text-a0e965d06c10025618939b4f85a5910a_h.jpg",//what we think we become
			"http://www.thequotepedia.com/images/108/mastering-others-is-strength-mastering-yourself-is-true-power-brain-quote.png",//mastering one self is true power
			"http://img.picturequotes.com/2/9/8870/brain-power-improves-by-brain-use-just-as-our-bodily-strength-grows-with-exercise-quote-1.jpg",//brain improves by brain use
			"https://weneedfun.com/wp-content/uploads/2016/08/Intelligence-Quotes-2.png",//intelligence = ability to change
			"https://lh3.googleusercontent.com/-WR0GDNUEgSA/VdjXV0Gz-CI/AAAAAAAAARk/xBo-DM4TiQ0/s512-Ic42/Buddha%252520Quotes%252520best%252520famous%252520pics%252520images%252520ideas%252520%252520%25252823%252529.jpg",//what you think you become
			"https://s-media-cache-ak0.pinimg.com/736x/a6/64/20/a6642038cc2e6468f54d55314386bb77.jpg",//no one saves us but us
			"https://www.brainyquote.com/photos/b/buddha101052.jpg",//do not dwell in the past
			"http://www.daimanuel.com/wp-content/uploads/2013/09/happiness.jpg",//a man asked budhha waht is happiness
			"https://s-media-cache-ak0.pinimg.com/736x/29/61/8e/29618eeffb35ac37fb28f4a2690a7570.jpg",//open you mouth only when > silence
			"https://weneedfun.com/wp-content/uploads/2015/09/Buddha-Quotes-15.jpg",//every morning we are born agaoin
			"https://lh3.googleusercontent.com/-pvfBGZn-GuI/VdjXjawkmnI/AAAAAAAAAVM/a9dR6KIUQZA/s512-Ic42/Buddha%252520Quotes%252520best%252520famous%252520pics%252520images%252520ideas%252520%252520%25252850%252529.jpg",//all unhapiness comes from not facing reality
			"http://www.keepinspiring.me/wp-content/uploads/2016/05/9-min.jpg",//if you light up somebody's lamp, it will also brighten your path
			"http://img.picturequotes.com/2/3/2330/intellectual-growth-should-commence-at-birth-and-cease-only-at-death-quote-1.jpg"
		
	};
	//total 13 urls

	public static String coverPicChooser(){
		//int i = (int) (Math.random()*100)%13;
		int numberOfUrls = coverPicList.length;//array.length returns the allocated(real size), not the logical size
		Random r = new Random();
		int i = r.nextInt(numberOfUrls - 0) + 0; //random integer between 0 (inclusive) and 13/numberOfUrls (exclusive)
		return coverPicList[i];
	}
}
