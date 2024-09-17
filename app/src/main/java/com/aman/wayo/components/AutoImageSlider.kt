import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aman.wayo.R
import com.aman.wayo.ui.theme.TertiaryVariantColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageSliderWithDots(auto:Boolean,height:Dp,images: List<Int>) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState) {
        while (auto) {
            // Wait for 2 seconds
            delay(5000)
            // Increment the page index
            val nextPage = (pagerState.currentPage + 1) % images.size
            // Scroll to the next page
            coroutineScope.launch {
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween

    ) {

        HorizontalPager(
            count = images.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
        ) { page ->
            Image(
                painter = painterResource(id = images[page]),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height)
                    .clip(RoundedCornerShape(20.dp))
                )
        }

        Spacer(modifier = Modifier.height(5.dp))

        // Dots Indicator
        DotsIndicator(
            totalDots = images.size,
            selectedIndex = pagerState.currentPage,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))

    }
}

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    dotSize: Dp = 10.dp,
    selectedDotSize: Dp = 10.dp,
    dotColor: Color = Color.Transparent,
    selectedDotColor: Color = TertiaryVariantColor
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        repeat(totalDots) { index ->
            val size = if (index == selectedIndex) selectedDotSize else dotSize
            val color = if (index == selectedIndex) selectedDotColor else dotColor

            Box(
                modifier = Modifier
                    .size(size)
                    .clip(CircleShape)
                    .background(color)
                    .border(width = 1.dp, shape = CircleShape, color = TertiaryVariantColor)
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun rememberPagerState(
    initialPage: Int = 0
): PagerState {
    return remember {
        PagerState(initialPage)
    }
}


@Preview
@Composable
fun PreviewSlider(){
    val slider1Images = listOf( R.drawable.slider1_img1, R.drawable.slider1_img2, R.drawable.slider1_img3, R.drawable.slider1_img4)
    ImageSliderWithDots(true,100.dp,images = slider1Images)

    val slider2Images = listOf( R.drawable.slider2_img1, R.drawable.slider2_img2)
    ImageSliderWithDots(false,150.dp,images = slider2Images)
}
