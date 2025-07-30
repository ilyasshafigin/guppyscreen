# Sets the minimum CMake version required for this project.
cmake_minimum_required(VERSION 3.22.1)

# Declares the project name. The project name can be accessed via ${ PROJECT_NAME},
# Since this is the top level CMakeLists.txt, the project name is also accessible
# with ${CMAKE_PROJECT_NAME} (both CMake variables are in-sync within the top level
# build script scope).
project("guppyscreen")

cmake_policy(SET CMP0077 OLD)

set(GUPPY_DIR ${CMAKE_CURRENT_SOURCE_DIR}/guppyscreen)

set(GUPPY_ASSET ${GUPPY_DIR}/assets/material/*.c)
if(DEFINED GUPPY_SMALL_SCREEN)
    set(GUPPY_ASSET ${GUPPY_DIR}/assets/material_46/*.c)
endif(DEFINED GUPPY_SMALL_SCREEN)

file(GLOB GUPPY_SRC ${GUPPY_DIR}/src/*.cpp
        ${GUPPY_DIR}/lv_touch_calibration/*.c
        ${GUPPY_ASSET}
        ${GUPPY_DIR}/assets/*.c
        lvgl-entrance.cpp)

list(REMOVE_ITEM GUPPY_SRC
        ${GUPPY_DIR}/src/wpa_event.cpp
        ${GUPPY_DIR}/src/wifi_panel.cpp
        ${GUPPY_DIR}/src/kd_graphic_mode.cpp
        ${GUPPY_DIR}/src/main.cpp)

include_directories(${GUPPY_DIR} ${GUPPY_DIR}/src ${GUPPY_DIR}/libhv/include ${GUPPY_DIR}/lv_touch_calibration)

add_library(${CMAKE_PROJECT_NAME} SHARED
        # List C/C++ source files with relative paths to this CMakeLists.txt.
        ${GUPPY_SRC})

set(BUILD_STATIC ON)
add_subdirectory(
        ${GUPPY_DIR}/libhv
        ./libhv
)

set(SPDLOG_BUILD_SHARED ON)
add_subdirectory(
        ${GUPPY_DIR}/spdlog
        ./spdlog
)

add_subdirectory(
        ${GUPPY_DIR}/lvgl
        ./lvgl
)
add_subdirectory(
        ${GUPPY_DIR}/lv_drivers
        ./lv_drivers
)

# Specifies libraries CMake should link to your target library. You
# can link libraries from various origins, such as libraries defined in this
# build script, prebuilt third-party libraries, or Android system libraries.
target_link_libraries(${CMAKE_PROJECT_NAME}
        # List libraries link to the target library
        lv_drivers
        lvgl
        hv
        spdlog
        android
        log)
