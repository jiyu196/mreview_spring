// 전역변수로  counter 0
// 10 ** 3  -> ** 이 제곱이라 1000
// 10의 0승은 1 이런

let counter = 0;
// function callLocal(n, cb) {
//   const add = 10 ** n;  // 0을 호출하면 이됨
//   counter += add;
//   console.log(`[local] 10^${n} - ${add} -> counter : ${counter}`);
//   if(cb) cb();
// }
// function callAsync(n, cb) {
//   const add = 10 ** n;  // 0을 호출하면 이됨
//   const delay =Math.floor(Math.random() * 1000);  // 0~ 999 사이의 난수가 나옴.
//
//   setTimeout(() => {
//   counter += add;
//   console.log(`[async] 10^${n} - ${add} -> counter : ${counter}, delay: ${delay}ms`);
//   if(cb) cb();
//   }, delay);
// }

function callLocal(n) {
  return new Promise((resolve, reject) => {
    const add = 10 ** n;  // 0을 호출하면 이됨
    counter += add;
    console.log(`[local] 10^${n} - ${add} -> counter : ${counter}`);
    resolve();
  });
}
function callAsync(n) {
  return new Promise((resolve) => {
    const add = 10 ** n;  // 0을 호출하면 이됨
    const delay =Math.floor(Math.random() * 1000);  // 0~ 999 사이의 난수가 나옴.

    setTimeout(() => {
      counter += add;
      console.log(`[async] 10^${n} - ${add} -> counter : ${counter}, delay: ${delay}ms`);
      resolve();
    }, delay);
  });
}
// callAsync(0)   // 호출하는법/ 리턴타입이 프로미스.
//     .then(() => callAsync(1))  // 리턴타입이 프로미스니까 then
//     .then(() => callAsync(2))
//     .then(() => callAsync(3))
//     .then(() => callAsync(4))
//     .then(() => callLocal(5))
//     .then(() => callLocal(6))
//     .then(() => callLocal(7))
//     .then(() => callLocal(8))
//     .then(() => callLocal(9))
//     .then(() => console.log("마지막"));  // log이후에는 프로미스를 반환하지 않기 때문에 then을 사용하지 못함.

// callback  => 추후에 호출되는. 람다도 콜백임. 파라미터로 전달되는 함수.
// 준비가됐을 때 호출될 이벤트.

// callAsync(0, () => {
//   callAsync(1,() => {
//     callAsync(2,() => {
//       callAsync(3,() => {
//         callAsync(4,() => {
//           callLocal(5, () => {
//             callLocal(6, () => {
//               callLocal(7, () => {
//                 callLocal(8, () => {
//                   callLocal(9, () => {
//                     console.log("마지막")
//                     })
//                   })
//                 })
//               })
//             })
//           })
//         })
//       })
//   });
// });

// 프로세스 순서 보장

// Promise 미래의 값이 성공(Resolve) 혹은 실패(Reject) 될 것이라는 약속된 결과를 표현
// const promise = new Promise((resolve, reject) => {
//   // if(성공) resolve(결과);
//   // else reject(에러);
// });

// 상태
// pending : 대기중
// fulfilled : 성공 (resolve가 호출된 상태)
// rejected : 실패 (reject가 호출된 상태)

// promise
//     .then(result => {/*성공시*/})
//     .catch(e => reject(e) /*실패시*/)
//     .finally(() => [/*무조건 실행*/])
//
// [].sort((a) => {});

function  asyncTask() {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve("완료")
    }, 500);
  })
}

// const result = asyncTask();
// result
//     .then(msg => {
//       console.log(msg)
//     })
//     .catch(err => {
//       console.log(err)
//     });
//
// // Tenable function
// asyncTask().then(console.log).catch(console.log);
//
// // 장점
// //
// Promise.race()

// callAsync(2);
// callAsync(3);
// callAsync(4);
// callLocal(5);
// callLocal(6);
// callLocal(7);
// callLocal(8);
// callLocal(9);
// 이제 이걸 순차처리하게 바꾸는. 5,6,7,8,9는 쉬운데 0,1,2,3,4,는..
//delay가 들어오는건 순번 보장이 되지 않는다는.

// $.ajax({
//   success: (date) => {
//     $.ajax({
//       success: (date) => {
//
//       }
//     })
//   }
// })

// $.ajax({
//   success: (data) => {},
//   error: (error) => {},
//   finally: (date) => {}
// })
// .done((data) => {
//
// })
// .fail(error => {
//
// })
// .always((data) => {
//
// })
// .done((data) => {
//
// })

// async, await
async function run() {
  await callAsync(0);
  await callAsync(1);
  await callAsync(2);
  await callAsync(3);
  await callAsync(4);
  await callLocal(5);
  await callLocal(6);
  await callLocal(7);
  await callLocal(8);
  console.log("마지막 직전. await이후")
  await callLocal(9);
  console.log("마지막. await이후")
}
// run();

// function fetchWithCallback(url = "", callback) { //url죽을수도 있으니까
//   fetch(url)
//       .then(response => response.json())
//       .then(data => {
//         console.log("콜백 결과", url)
//         console.log(data)
//         if(callback) callback();
//       }) // 이걸 순차호출하면 1번이, 3번이 , 뭐든 ㅁ먼저올 수 있음
// }

// fetchWithCallback("../replies/board/4")
// fetchWithCallback("../replies/board/5")  // 이렇게 하는건 순서보장 안한거.

function fetchReplies(bno) { //url죽을수도 있으니까
  return fetch(`../replies/board/${bno}`)
      .then(response => response.json())
      .then(data => {
        console.log("콜백 결과", bno)
        console.log(data)
      })
}
// fetchWithCallback("../replies/board/3", () => fetchWithCallback("../replies/board/4", () => fetchWithCallback("../replies/board/5", () => ("콜백지옥"))));
// fetchReplies(3)
//     .then(() => fetchReplies(4))
//     .then(() => fetchReplies(5))
//     .then(() => console.log('Promise Call 적용'));
(async() => {
  fetchReplies(3);
  fetchReplies(4);
  fetchReplies(5);
  console.log('Promise Call 적용')
})(); // IIFE