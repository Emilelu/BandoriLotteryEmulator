package me.emilelu.doriemu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class playMusic extends Thread {
	private String[] args;

	public void run() {
		try {
			Music.main(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class Lottery {
	public static void main(String[] args) {

		playMusic play = new playMusic();
		play.start();

		System.out.println("当前的星石数：∞");
		System.out.println("要来几回★KiraKira~★DokiDoki~★的十连抽呢！？");
		try (Scanner s = new Scanner(System.in)) {

			int times = s.nextInt();
			String[] stars = { "    ★★", "  ★★★", "★★★★" };
			List<String> storedStars = new ArrayList<String>();
			int timed = 1;

			for (int i = 0; i < times; i++) {
				System.out.println("Loading...");
//				Thread.sleep(500);

				System.out.println("现在是第 " + timed + " 回！");

				double four = 0.06;
				double three = 0.2605;
				double random = Math.random();
				if (timed % 10 == 0) {
					four = 0.3;
					three = 0.5;
				}
				if (random <= four && !storedStars.contains("★★★★")) {
					int hh = (int) (Math.random() * 2) + 1;
					for (int k = 0; k < hh; k++) {
						storedStars.add(stars[2]);
					}
				}
				if (random <= three && !storedStars.contains("  ★★★")) {
					int hh = (int) (Math.random() * 3) + 1;
					for (int k = 0; k < hh; k++) {
						storedStars.add(stars[1]);
					}
				}

				boolean ing = true;

				while (ing) {
					storedStars.add(stars[0]);
					if (storedStars.size() == 10) {
						ing = false;
					}
				}

				if (!storedStars.contains("  ★★★") && !storedStars.contains("★★★★")) { // 保底
					int toChange = (int) (Math.random() * storedStars.size());
					storedStars.set(toChange, "  ★★★");
				}

				for (int k = 0; k < storedStars.size(); k++) {
					if (k == 5) {
						System.out.println();
					}
					System.out.print(storedStars.get(k) + "\t");
				}

				storedStars.clear();
				timed++;
				System.out.println("\n");
			}

			try (Scanner exit = new Scanner(System.in)) {
				System.out.println("输入任意字符退出程序！");
				if (exit.hasNext()) {
					System.exit(0);
				}
			} catch (Exception e) {
				System.out.print("\n程序异常退出，详情：");
				e.printStackTrace();
			}

		} catch (Exception e) {
			System.out.print("\n程序异常退出，详情：");
			e.printStackTrace();
		}

	}
}
