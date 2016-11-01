package com.doodle.byheart.util;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * @see ��λ��������
 * @author ly-lihongliang
 */
public class LocUtils {

	private Context mContext;

	private LocationManager locationManager;
	private String provider;

	public Location location;
	public boolean locState;
	public List<Address> regionList;
	public Calendar updateTime;
	
	public LocUtils(Context context) {
		this.mContext = context;
	}

	public void execute() {
		// ��ȡLocationManager����
		locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
		// ��ȡLocation Provider
		getProvider();
		// ���δ����λ��Դ����GPS���ý���
		openGPS();
		// ��ȡλ��
		location = locationManager.getLastKnownLocation(provider);
		// ��ʾλ����Ϣ�����ֱ�ǩ
		updateWithNewLocation(location);
		// ע�������locationListener����2��3���������Կ��ƽ���gps��Ϣ��Ƶ���Խ�ʡ��������2������Ϊ���룬
		// ��ʾ����listener�����ڣ���3������Ϊ��,��ʾλ���ƶ�ָ�������͵���listener
		locationManager.requestLocationUpdates(provider, 0, 0,locationListener);
	}

	/**
	 * Gps��Ϣ������
	 */
	private final LocationListener locationListener = new LocationListener() {
		// λ�÷����ı�����
		public void onLocationChanged(Location location) {
			updateWithNewLocation(location);
		}

		// provider���û��رպ����
		public void onProviderDisabled(String provider) {
			updateWithNewLocation(null);
		}

		// provider���û����������
		public void onProviderEnabled(String provider) {
		}

		// provider״̬�仯ʱ����
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

	};

	/**
	 * �ж��Ƿ���GPS����δ��������GPS���ý���
	 */
	private void openGPS() {
		if (locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)
				|| locationManager.isProviderEnabled(android.location.LocationManager.NETWORK_PROVIDER)) {
			T.show(mContext, "λ��Դ�����ã�");
			return;
		}else{
			T.show(mContext, "λ��Դδ���ã�");
			// ת��GPS���ý���
//			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//			startActivityForResult(intent, 0);
		}
	}

	/**
	 * ��ȡLocation Provider
	 */
	private void getProvider() {
		// ����λ�ò�ѯ����
		Criteria criteria = new Criteria();
		// ��ѯ���ȣ���
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		// �Ƿ��ѯ��������
		criteria.setAltitudeRequired(false);
		// �Ƿ��ѯ��λ��:��
		criteria.setBearingRequired(false);
		// �Ƿ������ѣ���
		criteria.setCostAllowed(true);
		// ����Ҫ�󣺵�
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		// ��������ʵķ���������provider����2������Ϊtrue˵��,���ֻ��һ��provider����Ч��,�򷵻ص�ǰprovider
		provider = locationManager.getBestProvider(criteria, true);
	}

	/**
	 * Gps���������ã�����λ����Ϣ
	 * 
	 * @param location
	 */
	private void updateWithNewLocation(Location location) {
		if (location != null) {
			locState = true;
		} else {
			locState = false;
		}
		regionList = getAddressbyGeoPoint(location);
		updateTime = Calendar.getInstance();
	}

	/**
	 * ��ȡ��ַ��Ϣ
	 * 
	 * @param location
	 * @return
	 */
	public List<Address> getAddressbyGeoPoint(Location location) {
		List<Address> result = null;
		// �Ƚ�Locationת��ΪGeoPoint
		// GeoPoint gp=getGeoByLocation(location);
		try {
			if (location != null) {
				// ��ȡGeocoder��ͨ��Geocoder�Ϳ����õ���ַ��Ϣ
				Geocoder gc = new Geocoder(mContext, Locale.getDefault());
				result = gc.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
