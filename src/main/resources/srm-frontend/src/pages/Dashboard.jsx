// import React from "react";

// export default function Dashboard() {
//   return (
//     <div className="flex h-screen">
//       {/* Боковое меню */}
//       <aside className="w-64 bg-gray-800 text-white p-4 space-y-4">
//         <h1 className="text-xl font-bold">SRM-система</h1>
//         <nav className="space-y-2">
//           <a href="/suppliers" className="block hover:underline">Поставщики</a>
//           <a href="/products" className="block hover:underline">Товары</a>
//           <a href="/requests" className="block hover:underline">Заявки</a>
//           <a href="/deliveries" className="block hover:underline">Поставки</a>
//           <a href="/reports" className="block hover:underline">Отчёты</a>
//           <a href="/logs" className="block hover:underline">Взаимодействия</a>
//         </nav>
//       </aside>

//       {/* Основная панель */}
//       <main className="flex-1 bg-gray-100 p-6 overflow-auto">
//         {/* Верхняя панель */}
//         <div className="flex justify-between items-center mb-6">
//           <h2 className="text-2xl font-semibold">Главная</h2>
//           <div>Пользователь • <button className="underline">Выход</button></div>
//         </div>

//         {/* Блок статистики */}
//         <section className="mb-6">
//           <h3 className="text-xl font-medium mb-2">Статистика поставок</h3>
//           <div className="grid grid-cols-3 gap-4">
//             <div className="bg-white p-4 rounded shadow">Активных заявок: <strong>12</strong></div>
//             <div className="bg-white p-4 rounded shadow">Просрочено: <strong>2</strong></div>
//             <div className="bg-white p-4 rounded shadow">Выполнено: <strong>43</strong></div>
//           </div>
//         </section>

//         {/* Ближайшие действия */}
//         <section className="mb-6">
//           <h3 className="text-xl font-medium mb-2">Ближайшие действия</h3>
//           <ul className="list-disc list-inside bg-white p-4 rounded shadow">
//             <li>Проверить поставку по заявке #351</li>
//             <li>Создать заявку на зимние аксессуары</li>
//             <li>Обработать новых поставщиков</li>
//           </ul>
//         </section>

//         {/* Последние изменения */}
//         <section>
//           <h3 className="text-xl font-medium mb-2">Последние изменения</h3>
//           <div className="bg-white p-4 rounded shadow space-y-2">
//             <div>• Заявка #350 подтверждена</div>
//             <div>• Добавлен поставщик "ВелоТрейд"</div>
//             <div>• Обновлена позиция "Шлем X-Track"</div>
//           </div>
//         </section>
//       </main>
//     </div>
//   );
// }


import React from "react";

export default function Dashboard() {
  return (
    <div>
      <h1>Добро пожаловать в SRM Dashboard</h1>
    </div>
  );
}
