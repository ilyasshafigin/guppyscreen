cmake_minimum_required(VERSION 3.22.1)
set(CMAKE_CXX_STANDARD 17)

project("guppyscreen")
cmake_policy(SET CMP0077 OLD)

set(BUILD_STATIC ON)
set(SPDLOG_BUILD_SHARED ON)

set(GUPPY_DIR ${CMAKE_CURRENT_SOURCE_DIR}/guppyscreen)
set(GUPPY_ASSET ${GUPPY_DIR}/assets/material/*.c)
if(DEFINED GUPPY_SMALL_SCREEN)
    set(GUPPY_ASSET ${GUPPY_DIR}/assets/material_46/*.c)
endif(DEFINED GUPPY_SMALL_SCREEN)

file(
    GLOB GUPPY_SRC
    ${GUPPY_DIR}/src/*.cpp
    ${GUPPY_DIR}/lv_touch_calibration/*.c
    ${GUPPY_ASSET}
    ${GUPPY_DIR}/assets/*.c
    lvgl-entrance.cpp
)
list(
    REMOVE_ITEM GUPPY_SRC
    ${GUPPY_DIR}/src/wpa_event.cpp
    ${GUPPY_DIR}/src/wifi_panel.cpp
    ${GUPPY_DIR}/src/kd_graphic_mode.cpp
    ${GUPPY_DIR}/src/main.cpp
)

include_directories(
    ${GUPPY_DIR}
    ${GUPPY_DIR}/src
    ${GUPPY_DIR}/libhv/include
    ${GUPPY_DIR}/lv_touch_calibration
)

add_library(${CMAKE_PROJECT_NAME} SHARED ${GUPPY_SRC})

add_subdirectory(${GUPPY_DIR}/libhv ./libhv)
add_subdirectory(${GUPPY_DIR}/spdlog ./spdlog)
add_subdirectory(${GUPPY_DIR}/lvgl ./lvgl)
add_subdirectory(${GUPPY_DIR}/lv_drivers ./lv_drivers)

target_link_libraries(
    ${CMAKE_PROJECT_NAME}
    lv_drivers
    lvgl
    hv
    spdlog
    android
    log
)
