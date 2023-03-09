package edu.mines.csci448.examples.samodelkin

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.mines.csci448.examples.samodelkin.data.SamodelkinRepo
import edu.mines.csci448.examples.samodelkin.presentation.list.SamodelkinListScreen
import edu.mines.csci448.examples.samodelkin.presentation.navigation.SamodelkinNavHost
import edu.mines.csci448.examples.samodelkin.presentation.navigation.SamodelkinTopBar
import edu.mines.csci448.examples.samodelkin.presentation.viewmodel.ISamodelkinViewModel
import edu.mines.csci448.examples.samodelkin.presentation.viewmodel.PreviewSamodelkinViewModel
import edu.mines.csci448.examples.samodelkin.presentation.viewmodel.SamodelkinViewModel
import edu.mines.csci448.examples.samodelkin.presentation.viewmodel.SamodelkinViewModelFactory
import edu.mines.csci448.examples.samodelkin.ui.theme.SamodelkinTheme

class MainActivity : ComponentActivity() {
    companion object {
        private const val LOG_TAG = "448.MainActivity"
    }

    private lateinit var mSamodelkinViewModel: ISamodelkinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOG_TAG, "onCreate() called")

        val factory = SamodelkinViewModelFactory(this)
        mSamodelkinViewModel = ViewModelProvider(this, factory)[factory.getViewModelClass()]

        setContent {
            MainActivityContent(samodelkinViewModel = mSamodelkinViewModel)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "onResume() called")
    }

    override fun onPause() {
        Log.d(LOG_TAG, "onPause() called")
        super.onPause()
    }

    override fun onStop() {
        Log.d(LOG_TAG, "onStop() called")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(LOG_TAG, "onDestroy() called")
        super.onDestroy()
    }

    override fun onPostResume() {
        super.onPostResume()
        Log.d(LOG_TAG, "onPostResume() called")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d(LOG_TAG, "onAttachedToWindow() called")
    }

    override fun onContentChanged() {
        super.onContentChanged()
        Log.d(LOG_TAG, "onContentChanged() called")
    }

    override fun onDetachedFromWindow() {
        Log.d(LOG_TAG, "onDetachedFromWindow() called")
        super.onDetachedFromWindow()
    }

    override fun onEnterAnimationComplete() {
        super.onEnterAnimationComplete()
        Log.d(LOG_TAG, "onEnterAnimationComplete() called")
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        Log.d(LOG_TAG, "onPostCreate() called")
    }

    override fun isFinishing(): Boolean {
        Log.d(LOG_TAG, "isFinishing() called")
        return super.isFinishing()
    }

    override fun finish() {
        Log.d(LOG_TAG, "finish() called")
        super.finish()
    }
}

@Composable
private fun MainActivityContent(samodelkinViewModel: ISamodelkinViewModel) {
    val navController = rememberNavController()
    val context = LocalContext.current

    SamodelkinTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(topBar = {
                SamodelkinTopBar(navController = navController, samodelkinViewModel = samodelkinViewModel,
                    context = context)
            }) { contentPadding ->
                SamodelkinNavHost(navController = navController, samodelkinViewModel = samodelkinViewModel,
                    context = context, modifier = Modifier.padding(contentPadding))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val context = LocalContext.current
    MainActivityContent(samodelkinViewModel = PreviewSamodelkinViewModel(context))
}