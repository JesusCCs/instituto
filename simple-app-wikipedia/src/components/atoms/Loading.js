import * as React from 'react';
import Lottie from 'lottie-react-native';
import {View} from 'react-native';

export default function Loading() {
  return (
    <View style={{flex: 1, backgroundColor: 'white'}}>
      <Lottie
        source={require('../../assets/animation/loader.json')}
        autoPlay={true}
        loop={true}
      />
    </View>
  );
}
