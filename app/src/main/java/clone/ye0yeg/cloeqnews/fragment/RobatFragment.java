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
import clone.ye0yeg.cloeqnews.bean.RobotMSGBean;
import clone.ye0yeg.cloeqnews.utils.Utils;

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
        RobotAdapter robotAdapter = new RobotAdapter(Utils.getContext(), datas);
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

    }
}
