package com.wisoft.bottomnav.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.wisoft.bottomnav.DataClass.MyStoryClass
import com.wisoft.bottomnav.ViewHolder.DeeplinkViewModel
import com.wisoft.bottomnav.coroutines.ApiAdapter
import com.wisoft.bottomnav.databinding.FragmentDashboardBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.HashMap

class DashboardFragment : Fragment() {

    private val deeplinkViewModel: DeeplinkViewModel by activityViewModels()

    private var _binding: FragmentDashboardBinding? = null
    private var job: Job? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =  ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        deeplinkViewModel.getActionDataValue().observe(viewLifecycleOwner)
        {
            getJsonData(it)
        }
        return root
    }

    private fun getJsonData(idNumber: String)
    {
        Log.d("idnumber", "getJsonData()")
        try {
//            binding.recycler.layoutManager = LinearLayoutManager(requireActivity().applicationContext)
//            binding.recycler.adapter = myStoryRecyclerViewAdapter
            job = viewLifecycleOwner.lifecycleScope.launch {
                try {
                    val actionData: MutableMap<String, String> = HashMap()
                    actionData["IDNumber"] = idNumber
                    val response = ApiAdapter.apiClient.getID(actionData)
                    if (response.isSuccessful && response.body() != null) {
                        val menuData = response.body()!!
                        Log.d("idnumber", menuData.IDNumber)
                        _binding!!.textIdNumber.text = menuData.IDNumber
                        _binding!!.textFirstName.text = menuData.FirstName
                        _binding!!.textLastName.text = menuData.lastName
                        _binding!!.textAddress.text = menuData.Address
                        _binding!!.textPhoneNumber.text = menuData.PhoneNumber
                        Log.d("idnumber", menuData.FirstName)
                        Log.d("idnumber", menuData.lastName)
                    }else
                    {
                        Log.d("idnumber", "reponse error")
                    }
                }catch (e: Exception)
                {
                    Log.d("idnumber", e.message.toString())
                }

//                if (response.isSuccessful && response.body() != null) {
//                    val menuData = response.body()!!
//                    val itemsList: ArrayList<basicInfo> = ArrayList()
//                    menuData.Items.forEach()
//                    {
//                        Log.d("idnumber", it.IDNumber)
//                    }
//                }
            }
        }catch ( e: Exception)
        {
            Log.d("ex", e.message.toString())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewsChildFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(position: String, IsNewCategory: Boolean=true ) =
            DashboardFragment().apply {
                arguments = Bundle().apply {
                    putString("NewsCategory", position)
                    putBoolean("IsNewCategory", IsNewCategory)
                }
            }
    }
}