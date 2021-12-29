package com.example.blooddonor;


import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import java.util.ArrayList;

public class donerAdapter extends RecyclerView.Adapter<donerAdapter.donerViewHolder>{

    private String[] data;
    private String[] location;
    private String[] status;
    private String[] bloodGroup;
    private ArrayList<User> users;
    public donerAdapter(ArrayList<User> users){
        this.users = (ArrayList<User>) users.clone();
    }

    @Override
    public donerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_item,viewGroup,false);
        return new donerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(donerViewHolder donerViewHolder, int i) {

        String name = users.get(i).getName();
        String loc = users.get(i).getLocation();
        String stat = users.get(i).getStatus();;
        String bg = users.get(i).getBloodGroup();;
        donerViewHolder.name.setText(name);
        donerViewHolder.location.setText(loc);
        donerViewHolder.status.setText(stat);
        donerViewHolder.bloodGroup.setText(bg);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

//    @Override
//    public Filter getFilter() {
//        return nameFilter;
//    }
//    private Filter nameFilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence charSequence) {
//            List<String> filteredList = new ArrayList<>();
//            if(charSequence == null || charSequence.length() == 0){
//                filteredList.addAll(listNameFull);
//            }else{
//                String filterPattern = charSequence.toString().toLowerCase(Locale.ROOT).trim();
//                for(String name:listNameFull){
//                    if(name.toLowerCase(Locale.ROOT).contains(filterPattern)){
//                        filteredList.add(name);
//                    }
//                }
//            }
//            FilterResults results = new FilterResults();
//            results.values = filteredList;
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//            listName.clear();
//            listName.addAll((List) filterResults.values);
//            notifyDataSetChanged();
//        }
//    };
    public class donerViewHolder extends RecyclerView.ViewHolder{
        TextView bloodGroup,name,location,about,status;
        public donerViewHolder(View itemView){
            super(itemView);
            bloodGroup = (TextView) itemView.findViewById(R.id.bloodGroup);
            name = (TextView) itemView.findViewById(R.id.name);
            location = (TextView) itemView.findViewById(R.id.location);
            about = (TextView) itemView.findViewById(R.id.about);
            status = (TextView) itemView.findViewById(R.id.status);
        }
    }

}
