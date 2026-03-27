package com.kidemma.extensions

import com.google.common.truth.Subject

/*
 * File: TruthExtensions
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */

inline fun <reified T> Subject.isInstanceOfK() {
    isInstanceOf(T::class.java)
}
