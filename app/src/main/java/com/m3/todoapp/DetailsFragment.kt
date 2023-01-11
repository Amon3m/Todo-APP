package com.m3.todoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.m3.todoapp.database.TasksDataBase
import com.m3.todoapp.database.model.Task
import com.m3.todoapp.databinding.FragmentDetailsBinding


class DetailsFragment(var position: Int,var task: Task) : DialogFragment() {
    lateinit var binding: FragmentDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentDetailsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title =TasksDataBase.getInstance(requireContext()).tasksDao().getItemTask(task.title.toString()).title
        val description =TasksDataBase.getInstance(requireContext()).tasksDao().getItemTask(task.title.toString()).description
        val iscompleted =TasksDataBase.getInstance(requireContext()).tasksDao().getItemTask(task.title.toString()).iscomplited
        binding.titlefrg.text=title.toString()
        binding.descfrg.text=description.toString()
        binding.Completedfrg.isChecked=iscompleted?:false
    }
    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
        }
    }

}



