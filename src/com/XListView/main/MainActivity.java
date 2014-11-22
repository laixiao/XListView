package com.XListView.main;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import android.app.Activity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class MainActivity extends Activity {
	//1.����ˢ��ListView�������������ԣ�
	PullToRefreshListView list;
	//2.����ʱ��ʾ��Layout����ʵ���Ѻ���ʾ��
	private ILoadingLayout loadingLayout;
	//3.��ʾ���ݵ�ListView��������ʾ���ݣ�
	private ListView listview;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        list=(PullToRefreshListView)this.findViewById(R.id.list);
        //����������ģʽ����������ģʽ��
        list.setMode(Mode.BOTH);
        //����һ���������,���������÷���������LoadingLayouts(��ͼ��ʾ����/ˢ��)��
        loadingLayout=list.getLoadingLayoutProxy();
        loadingLayout.setLastUpdatedLabel("");
		loadingLayout.setPullLabel(getString(R.string.pull_to_refresh_bottom_pull));//�����϶�
		loadingLayout.setRefreshingLabel(getString(R.string.pull_to_refresh_bottom_refreshing));//���ڻ�ȡ..
		loadingLayout.setReleaseLabel(getString(R.string.pull_to_refresh_bottom_release));//���ڻ�ȡ..
        
		//1.���û�������
		list.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
				//��ʼ��������������
				if (firstVisibleItem == 0) {
					loadingLayout.setLastUpdatedLabel("");
					loadingLayout.setPullLabel(getString(R.string.pull_to_refresh_top_pull));//����ˢ��
					loadingLayout.setRefreshingLabel(getString(R.string.pull_to_refresh_top_refreshing));//������....
					loadingLayout.setReleaseLabel(getString(R.string.pull_to_refresh_top_release));//�ͷ�ˢ��
				} else if (firstVisibleItem + visibleItemCount + 1 == totalItemCount) {
					loadingLayout.setLastUpdatedLabel("");
					loadingLayout.setPullLabel(getString(R.string.pull_to_refresh_bottom_pull));//�����϶�
					loadingLayout.setRefreshingLabel(getString(R.string.pull_to_refresh_bottom_refreshing));//���ڻ�ȡ..
					loadingLayout.setReleaseLabel(getString(R.string.pull_to_refresh_bottom_release));//���ڼ��ظ���..
				}
				
			}
		});
		
		//2. ����ˢ�¼���
		list.setOnRefreshListener(new OnRefreshListener2<ListView>() {

					@Override
					public void onPullDownToRefresh(
							PullToRefreshBase<ListView> refreshView) {
						// 0.����ˢ��(�ӵ�һҳ��ʼװ������)
						//queryData(0, STATE_REFRESH);
						Toast.makeText(MainActivity.this, "����ˢ��(�ӵ�һҳ��ʼװ������)", Toast.LENGTH_LONG).show();
						list.onRefreshComplete();
					}

					@Override
					public void onPullUpToRefresh(
							PullToRefreshBase<ListView> refreshView) {
						// 0.�������ظ���(������һҳ����)
						//queryData(curPage, STATE_MORE);
						Toast.makeText(MainActivity.this, "�������ظ���(������һҳ����)", Toast.LENGTH_LONG).show();
						list.onRefreshComplete();
					}
				});
		
		//3.��ȡlistview
		listview=list.getRefreshableView();
		//4.������������listview.setAdapter(new firstListAdapter(this));
    }
}
