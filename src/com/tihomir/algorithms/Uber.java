package com.tihomir.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Uber {

	static class Context {
		Long date;
		String price;

		Context(String p, Long d) {
			price = p;
			date = d;
		}
	}

	private static void addPrice(String currency, String price) {
		Long time = System.nanoTime();
		Context ctx = new Context(price, time);
		if (priceToCtx.containsKey(currency)) {
			List<Context> infos = priceToCtx.get(currency);
			infos.add(ctx);
		} else {
			List<Context> infos = new ArrayList<>();
			priceToCtx.put(currency, infos);
			infos.add(ctx);
		}
	}

	private static String getPrice(String currency, long time) {
		List<Context> currInfo = priceToCtx.get(currency);
		return getClosestCtxInfo(currInfo, time).price;
	}

	private static Context getClosestCtxInfo(List<Context> currInfo, Long time) {
		for (int i = 1; i < currInfo.size(); i++) {
			Context ctx = currInfo.get(i);
			Long ctime = ctx.date;
			if (ctime > time) {
				return currInfo.get(i - 1);
			}
		}
		return currInfo.get(currInfo.size() - 1);
	}

	private static Map<String, List<Context>> priceToCtx = new HashMap<>();

	public static void main(String[] args) throws InterruptedException {
		addPrice("usd", "1.50");
		Long prev = System.nanoTime();
		addPrice("usd", "1.52");
		addPrice("usd", "1.54");
		addPrice("usd", "1.55");
		System.out.println(getPrice("usd", prev));
		System.out.println(getPrice("usd", System.nanoTime()));
	}
}