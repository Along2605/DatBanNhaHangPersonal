package gui;

import entity.BanAn;
//////
@FunctionalInterface
public interface BanAnCallback {
	void onBanSelected(BanAn banNguon, BanAn banDich, boolean success);
}
