package com.m3.todoapp.activity

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.m3.islami2.base.BaseActivity
import com.m3.todoapp.R
import com.m3.todoapp.database.TasksDataBase
import com.m3.todoapp.database.model.Task
import com.m3.todoapp.databinding.ActivityAddTaskBinding

class AddTaskActivity : BaseActivity(){
    lateinit var binding: ActivityAddTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()


        binding.saveBtn.setOnClickListener{
            addTask()

        }
    }


    private fun addTask() {
        if(!validData())return

        val tasktitle=binding.titleLayout.editText?.text.toString()
        val taskdescription=binding.descLayout.editText?.text.toString()
        val iscomp=binding.Completed.isChecked

        val task=Task(title=tasktitle, description = taskdescription, iscomplited = iscomp)

        TasksDataBase.getInstance(applicationContext)
            .tasksDao()
            .addTask(task)
        showDialoge(messageId = R.string.task_added_successfully,
            posActionName = R.string.ok,
            posAction = DialogInterface.OnClickListener{dialog,which->
                dialog.dismiss()
                finish()
            })


//        showDialoge(messageId = R.string.task_added_successfully,
//            posActionName = R.string.ok,
//            posAction = DialogInterface.OnClickListener{dialog,which->
//                dialog.dismiss()
//                finish()
//            })
    }





    private fun validData(): Boolean {
        var isValid=true
        if (binding.titleLayout.editText?.text.toString().isBlank()){
            binding.titleLayout.error="please enter valid value"
            isValid=false
        }
        if (binding.descLayout.editText?.text.toString().isBlank()){
            binding.descLayout.error="please enter valid value"
            isValid=false
        }
        return isValid


    }

    private fun setupViews() {
        binding.titleLayout.editText?.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                binding.titleLayout.error=null

            }
        })
        binding.descLayout.editText?.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                binding.descLayout.error=null

            }
        })


    }

}