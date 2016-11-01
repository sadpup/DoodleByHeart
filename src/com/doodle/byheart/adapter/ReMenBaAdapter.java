package com.doodle.byheart.adapter;

import java.util.HashMap;
import java.util.List;

import com.doodle.byheart.R;
import com.doodle.byheart.util.L;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @see �����б��listView������
 * @author ly-lihongliang
 */
public class ReMenBaAdapter extends BaseAdapter{

	private LayoutInflater mInflater;// ��̬����ӳ��
	private List<HashMap<String, Object>> mData;
	
	public ReMenBaAdapter(Context context,List<HashMap<String, Object>> mData) {
		this.mInflater = LayoutInflater.from(context);
		this.mData = mData;
	}
	
	@Override
	public int getCount() {
		// TODO �ڴ�������������������ݼ��е���Ŀ��
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO ��ȡ���ݼ�����ָ��������Ӧ��������
		return 0;
	}

	@Override
	public long getItemId(int position) {
		// TODO ȡ���б�����ָ��������Ӧ����id
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vHolder = null; 
	    //���convertView����Ϊ���򴴽��¶��󣬲�Ϊ������    
	    if (convertView == null) {  
	        convertView = mInflater.inflate(R.layout.list_remen_item, null);  //���ݲ����ļ�ʵ����view\
	        // ���� ViewHodler ����    
	        vHolder = new ViewHolder();  
	        vHolder.title= (TextView) convertView.findViewById(R.id.title);//��ĳ���ؼ� 
	        vHolder.time= (TextView) convertView.findViewById(R.id.time);//��ĳ���ؼ� 
	        vHolder.info= (TextView) convertView.findViewById(R.id.info);
	        vHolder.img= (ImageView) convertView.findViewById(R.id.img);  
	        // ��ViewHodler���浽Tag��(Tag���Խ���Object���Ͷ��������κζ��������Ա���������)  
	        convertView.setTag(vHolder);  
	    } else {  
	        //��convertView��Ϊ��ʱ��ͨ��getTag()�õ�View    
	        vHolder = (ViewHolder) convertView.getTag();   
	    }  
	    // ������ֵ���޸���ʾ��ֵ    
	    vHolder.title.setText(mData.get(position).get("title").toString());//���ÿؼ���������(���ݴӼ���������)
	    vHolder.time.setText(mData.get(position).get("time").toString());//���ÿؼ���������(���ݴӼ���������)
	    vHolder.info.setText(mData.get(position).get("info").toString());
	    vHolder.img.setBackgroundResource((Integer) mData.get(position).get("img"));
	    return convertView;  
	}

	static class ViewHolder {
		TextView title;
		TextView time;
		TextView info;
		ImageView img;
	}
	
}
