package com.solution_challenge_2022.findit.findit_feature.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.solution_challenge_2022.findit.databinding.FragmentHomeBinding
import com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.CampusInfoActivity
import com.solution_challenge_2022.findit.util.Constant

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.hcmutPlace.setOnClickListener {
            val gotoCampusInfo = Intent(context, CampusInfoActivity::class.java)
            gotoCampusInfo.putExtra(Constant.QR_CODE_KEY, "hcmut-a4")
            startActivity(gotoCampusInfo)
        }

        binding.secondCard.setOnClickListener {
            Toast.makeText(
                context,
                "This is static data, only the first card can be pressed. Or you can scan the QR in our github repo",
                Toast.LENGTH_LONG
            ).show()

        }
        binding.thirdCard.setOnClickListener {
            Toast.makeText(
                context,
                "This is static data, only the first card can be pressed. Or you can scan the QR in our github repo",
                Toast.LENGTH_LONG
            ).show()

        }
    }
}
