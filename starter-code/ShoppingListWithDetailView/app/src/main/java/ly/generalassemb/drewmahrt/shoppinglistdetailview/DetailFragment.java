package ly.generalassemb.drewmahrt.shoppinglistdetailview;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DetailFragment extends Fragment {
    private static final String SHOPPING_ITEM_ID = "param1";

    private int mItemId;

    private OnShoppingListItemClickListener mListener;

    public DetailFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(int shoppingItemId) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(SHOPPING_ITEM_ID, shoppingItemId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mItemId = getArguments().getInt(SHOPPING_ITEM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ShoppingSQLiteOpenHelper shoppingSQLiteOpenHelper = ShoppingSQLiteOpenHelper.getInstance(getContext());

        if (getArguments()!=null){
            mItemId = getArguments().getInt(SHOPPING_ITEM_ID);
        }

        ShoppingItem shoppingItem = shoppingSQLiteOpenHelper.getShoppingItemById(mItemId);

        TextView nameView = (TextView)view.findViewById(R.id.detail_name);
        TextView descView = (TextView)view.findViewById(R.id.detail_description);
        TextView priceView = (TextView)view.findViewById(R.id.detail_price);
        TextView typeView = (TextView)view.findViewById(R.id.detail_category);

        nameView.setText(shoppingItem.getName());
        descView.setText(shoppingItem.getDescription());
        priceView.setText(shoppingItem.getPrice());
        typeView.setText(shoppingItem.getType());


    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onShoppingListItemClick(mItemId);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnShoppingListItemClickListener) {
            mListener = (OnShoppingListItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnShoppingListItemClickListener {
        void onShoppingListItemClick(int shoppingItem_id);
    }
}
