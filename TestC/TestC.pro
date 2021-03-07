TEMPLATE = app

QT += qml quick
CONFIG += c++11

RESOURCES += TestC.qrc

qml.files = TestC.qml

launch_modeall {
	CONFIG(debug, debug|release) {
	    DESTDIR = debug
	} else {
	    DESTDIR = release
	}
}

SOURCES += TestC.cpp
