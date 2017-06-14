package clone.ye0yeg.cloeqnews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import clone.ye0yeg.cloeqnews.R;
import clone.ye0yeg.cloeqnews.adapter.MsgReceivedtemDelagate;
import clone.ye0yeg.cloeqnews.adapter.MsgSendtemDelagate;
import clone.ye0yeg.cloeqnews.adapter.RobotAdapter;
import clone.ye0yeg.cloeqnews.base.Constant;
import clone.ye0yeg.cloeqnews.bean.RobotBean;
import clone.ye0yeg.cloeqnews.bean.RobotMSGBean;
import clone.ye0yeg.cloeqnews.net.QClitent;
import clone.ye0yeg.cloeqnews.net.QNewsService;
import clone.ye0yeg.cloeqnews.utils.LogUtils;
import clone.ye0yeg.cloeqnews.utils.Utils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 6/5/2017.
 */

public class RobatFragment extends Fragment implements TextView.OnEditorActionListener {

    @BindView(R.id.tb_robot_tb)
    Toolbar tb_tb;
    @BindView(R.id.rv_robot_content)
    RecyclerView rv_content;
    @BindView(R.id.et_robot_input)
    EditText et_input;
    @BindView(R.id.bt_robot_send)
    Button bt_send;
    private RobotAdapter robotAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_robot, null);
        ButterKnife.bind(this, view);
        //设置输入框的状态
        et_input.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        et_input.setInputType(EditorInfo.TYPE_CLASS_TEXT);

        //实时添加数据

        List<RobotMSGBean> datas = new ArrayList<>();

        //添加Listen
        et_input.setOnEditorActionListener(this);

        //添加到adapter ， 该adapter中， 添加不同holder（item）。
        robotAdapter = new RobotAdapter(Utils.getContext(), datas);
        robotAdapter.addItemViewDelegate(new MsgReceivedtemDelagate());
        robotAdapter.addItemViewDelegate(new MsgSendtemDelagate());
        rv_content.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_content.setAdapter(robotAdapter);

        RobotMSGBean robotMSGBean = new RobotMSGBean();
        robotMSGBean.setMsg("Well Come To AI!");
        robotMSGBean.setType(robotMSGBean.MSG_RECEIVED);
        robotAdapter.addDataToAdapter(robotMSGBean);
        robotAdapter.notifyDataSetChanged();
        rv_content.smoothScrollToPosition(robotAdapter.getItemCount() - 1);
        return view;
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }

    /*
    * send 的点击事件
    * */
    @OnClick(R.id.bt_robot_send)
    public void onClick(View view) {
        sendMsg();
    }

    private void sendMsg() {
        String input = et_input.getText().toString();
        if (TextUtils.isEmpty(input)) {
            et_input.setError("不为空");
            return;
        }
        //发送的界面
        RobotMSGBean sendBean = new RobotMSGBean();
        sendBean.setMsg(input);
        sendBean.setType(RobotMSGBean.MSG_SEND);
        et_input.setText("");
        //添加到适配器中
        robotAdapter.addDataToAdapter(sendBean);
        robotAdapter.notifyDataSetChanged();
        //网络请求数据
        QClitent.getInstance()
                .create(QNewsService.class, Constant.BASE_Q_A_ROBOT_URL)
                .getQARobotData(input)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RobotBean>() {
                    @Override
                    public void accept(RobotBean robotBean) throws Exception {
                        //接收的界面
                        String text = robotBean.getResult().getText();
                        RobotMSGBean receverBean = new RobotMSGBean();
                        receverBean.setMsg(text);
                        receverBean.setType(RobotMSGBean.MSG_RECEIVED);
                        robotAdapter.addDataToAdapter(receverBean);
                        robotAdapter.notifyDataSetChanged();
                        //将界面转向最下面那条
                        rv_content.scrollToPosition(robotAdapter.getItemCount() - 1);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.i("error: " + throwable.getMessage());
                    }
                });


    }

}
