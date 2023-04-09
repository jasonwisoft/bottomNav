package com.wisoft.bottomnav.ui.home

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
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wisoft.bottomnav.DataClass.MyStoryClass
import com.wisoft.bottomnav.ModelAdapter.MyStoryRVAdapter
import com.wisoft.bottomnav.R
import com.wisoft.bottomnav.ViewHolder.DeeplinkViewModel
import com.wisoft.bottomnav.ViewHolder.MyStoryRecyclerViewHolder
import com.wisoft.bottomnav.coroutines.ApiAdapter
import com.wisoft.bottomnav.databinding.FragmentHomeBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class HomeFragment : Fragment(), MyStoryRecyclerViewHolder.Delegate {
    private val deeplinkViewModel: DeeplinkViewModel by activityViewModels()

    private var _binding: FragmentHomeBinding? = null
    private var navController: NavController? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var job: Job? = null

    private val myStoryRecyclerViewAdapter by lazy {
        MyStoryRVAdapter(
            ArrayList(), requireActivity(), this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getJsonData()
    }

    private fun getJsonData()
    {
        Log.d("idnumber", "getJsonData()")
        try {
            binding.recycler.layoutManager = LinearLayoutManager(requireActivity().applicationContext)
            binding.recycler.adapter = myStoryRecyclerViewAdapter
            job = viewLifecycleOwner.lifecycleScope.launch {
                try {
                    Log.d("idnumber", "job()")
                    val response = ApiAdapter.apiClient.getList()
                    if (response.isSuccessful && response.body() != null) {
                        val menuData = response.body()!!
                        val itemsList: ArrayList<MyStoryClass> = ArrayList()
                        var nindex : Long = 0
                        menuData.Items.forEach()
                        {
                            nindex += 1
                            Log.d("idnumber", it.IDNumber)
                            val mydata = MyStoryClass(
                                nindex,
                                it.IDNumber,
                                it.FirstName,
                                it.lastName,
                                it.Address,
                                it.PhoneNumber,
                                "",
                                true,
                            )
                            itemsList.add(mydata)
                        }
                        myStoryRecyclerViewAdapter.loadNewData(itemsList)
                        binding.recycler.adapter = myStoryRecyclerViewAdapter
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

    override fun onItemClickListener(view: View, saveItems: MyStoryClass) {
        //TODO("Not yet implemented")
        Log.d("idnumber",saveItems.IDNumber)
        deeplinkViewModel.setActionDataValue(saveItems.IDNumber)
        val bottomNavigationView: BottomNavigationView = activity?.findViewById(R.id.nav_view) as BottomNavigationView
        bottomNavigationView.selectedItemId = R.id.navigation_dashboard
        //bottomNavigationView.menu.getItem(1).isChecked = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}