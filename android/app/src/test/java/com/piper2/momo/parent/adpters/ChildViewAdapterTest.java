package com.piper2.momo.parent.adpters;

import android.content.Context;

import com.piper2.momo.android.digitalbursar.models.Child;

import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ChildViewAdapterTest {

    @Mock
    Context context;

    private ChildViewAdapter childViewAdapter;

    @Test
    public void noFailOnNullChildrenList() {
        childViewAdapter = new ChildViewAdapter(context, null);
        assertEquals(1,childViewAdapter.getItemCount());
    }

    @Test
    public void returnOneOnEmptyChildrenList(){
        childViewAdapter = new ChildViewAdapter(context, new ArrayList<>());
        assertEquals(1,childViewAdapter.getItemCount());
    }

    @Test
    public void returnFullListOfChildren(){
        childViewAdapter = new ChildViewAdapter(context, Arrays.asList(new Child(),new Child(), new Child()));
        assertEquals(3, childViewAdapter.getItemCount());
    }
}