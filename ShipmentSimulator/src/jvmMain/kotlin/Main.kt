// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application



@Composable
fun App(simulator: TrackingSimulator) {
    var simulator = simulator
    val trackers = remember { mutableListOf<Any>() }

    MaterialTheme {
        var input by remember { mutableStateOf("")}

        Column {
            Row {
                Column (
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row {
                        Column {
                            TextField(
                                value = input,
                                onValueChange = { input = it },
                                label = { Text("Shipment ID") }
                            )
                        }
                        Column {
                            Button(
                                onClick = {
                                    if (input != "") {
                                        val id = input
                                        input = ""
                                        if (simulator.findShipment(id) != null) {
                                            val tracker = TrackerViewHelper(simulator.findShipment(id)!!)
                                            trackers.add(tracker)
                                        } else {
                                            trackers.add(id)
                                        }
                                    }
                                }
                            ) {
                                Text("Track ID")
                            }
                        }
                    }
                }
            }

            Row {
                LazyColumn (
                    verticalArrangement = Arrangement.spacedBy(10.dp),



                ) {
                    items(items = trackers, itemContent = { item ->
                        Row (
                            modifier = Modifier
                                .height(100.dp)
                                .background(androidx.compose.ui.graphics.Color.LightGray)
                                .fillMaxWidth(1f)
                        ) {
                            Column {

                            }

                            Column(
                                horizontalAlignment = Alignment.End
                            ) {
                                Button( onClick = {
                                    trackers.remove(item)
                                }) {
                                    Text("Remove")
                                }
                            }

                        }


                    })

                }
            }
        }



    }
}

fun createTracker(id: String) {

}

fun main() = application {
    val simulator = TrackingSimulator("/Users/stephen/git/cs-5700-assn2/ShipmentSimulator/src/jvmMain/kotlin/test.txt")
    simulator.runSimulator()
    Window(onCloseRequest = ::exitApplication) {
        App(simulator)
    }
}