package com.m3.todoapp.activity

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.m3.islami2.base.BaseActivity
import com.m3.todoapp.Conestent
import com.m3.todoapp.R
import com.m3.todoapp.TaskAdapter
import com.m3.todoapp.database.TasksDataBase
import com.m3.todoapp.database.model.Task
import com.m3.todoapp.databinding.ActivityEditBinding

class UpdateActivity : BaseActivity() {
    lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intTitle=intent.getStringExtra(Conestent.Extra_Tasks)
        val intID:Int=intent.getIntExtra(Conestent.Extra_Id,0)

        val title = TasksDataBase.getInstance(applicationContext).tasksDao().getItemTask(intTitle!!).title
        val description = TasksDataBase.getInstance(applicationContext).tasksDao().getItemTask(intTitle).description
        val isCompleted = TasksDataBase.getInstance(applicationContext).tasksDao().getItemTask(intTitle).iscomplited

        binding.editTitle.setText(title.toString())
        binding.editDesc.setText(description.toString())
        binding.Completed.isChecked=isCompleted?:false
        setupViews()

        binding.editBtn.setOnClickListener {
            updateTask(intID)

        }

    }
    private fun updateTask(intID: Int) {
        if(!validData())return

        val taskTitle=binding.editTitleLayout.editText?.text.toString()
        val taskDescription=binding.editDescLayout.editText?.text.toString()
        val isComp=binding.Completed.isChecked

        val task=Task(id =intID ,title=taskTitle, description = taskDescription, iscomplited = isComp)

        TasksDataBase.getInstance(applicationContext)
            .tasksDao()
            .updateTask(task)
        showDialoge(messageId = R.string.task_edited_successfully,
            posActionName = R.string.ok,
            posAction = DialogInterface.OnClickListener{ dialog, which->
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
        if (binding.editTitleLayout.editText?.text.toString().isBlank()){
            binding.editTitleLayout.error="please enter valid value"
            isValid=false
        }
        if (binding.editDescLayout.editText?.text.toString().isBlank()){
            binding.editDesc.error="please enter valid value"
            isValid=false
        }
        return isValid


    }

    private fun setupViews() {
        binding.editTitleLayout.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                binding.editTitleLayout.error=null

            }
        })
        binding.editDescLayout.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                binding.editTitleLayout.error=null

            }
        })


    }



}



