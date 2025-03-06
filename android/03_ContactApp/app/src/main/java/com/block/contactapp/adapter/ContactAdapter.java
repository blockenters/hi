package com.block.contactapp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.block.contactapp.MainActivity;
import com.block.contactapp.R;
import com.block.contactapp.UpdateActivity;
import com.block.contactapp.model.Contact;

import java.util.ArrayList;

// 1. 상속받는다.  // 3. 만든 뷰홀더를 적어준다. // 4. 오버라이드 함수 눌러서 에러 없앤다.
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    // 5. 이 어댑터 클래스의 멤버 변수만든다.
    Context context;
    ArrayList<Contact> contactArrayList;

    // 6. 생성자 만든다.
    public ContactAdapter(Context context, ArrayList<Contact> contactArrayList) {
        this.context = context;
        this.contactArrayList = contactArrayList;
    }

    // 7. 아래 3개함수를 구현한다.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_row, parent, false);
        return new ContactAdapter.ViewHolder(view);
    }

    // 어레이리스트에 있는 데이터를, 화면에 표시하는 함수.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // 데이터를 끄집어 내고
        Contact contact = contactArrayList.get(position);

        // 화면에 표시
        holder.txtName.setText(contact.name);
        holder.txtPhone.setText(contact.phone);
    }

    @Override
    public int getItemCount() {
        return contactArrayList.size();
    }

    // 2. 뷰 홀더 클래스를 만든다.
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtPhone;
        ImageView imgDelete;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtPhone = itemView.findViewById(R.id.txtPhone);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            cardView = itemView.findViewById(R.id.cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 유저가 누른 리스트의 인덱스를 가져온다.
                    int index = getAdapterPosition();
                    // 인덱스를 알면, 해당 위치의 데이터를 가져올수 있다.
                    Contact contact = contactArrayList.get(index);

                    // 연락처수정 액티비티를 띄운다.
                    Intent intent = new Intent(context, UpdateActivity.class);
                    intent.putExtra("contact", contact);
                    intent.putExtra("index", index);
                    ((MainActivity)context).launcher.launch(intent);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("연락처 삭제");
                    builder.setMessage("정말 삭제하시겠습니까?");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int index = getAdapterPosition();
                            contactArrayList.remove(index);
                            notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("CANCEL", null);
                    builder.show();
                }
            });
        }
    }
}
