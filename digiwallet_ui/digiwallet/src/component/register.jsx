import { useForm } from "react-hook-form";
import { useState } from "react";
import { useNavigate } from "react-router-dom"; // Import useNavigate

function Register() {
  const { register, handleSubmit, reset } = useForm();
  const [message, setMessage] = useState(""); // Success/Error message
  const navigate = useNavigate(); // Hook for navigation

  const onSubmit = async (data) => {
    try {
      const response = await fetch("http://localhost:8080/api/accounts", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data),
      });

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || "Account creation failed");
      }

      // Account successfully created
      setMessage("Account created successfully! Redirecting...");
      reset();

      // Redirect to the "/" route after 2 seconds
      setTimeout(() => navigate("/"), 2000);
    } catch (error) {
      setMessage(error.message);
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen">
      <div className="max-w-lg mx-auto w-5/5 p-6 bg-white shadow-md rounded-lg">
        <h2 className="text-2xl font-bold mb-4 text-center">Create Account</h2>
        {message && <p className="text-center text-red-500">{message}</p>}
        <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
          <div>
            <label className="block font-semibold">Account Number</label>
            <input
              type="number"
              {...register("accountNumber", { required: true })}
              className="w-full p-2 border rounded"
            />
          </div>
          <div>
            <label className="block font-semibold">Account Holder Name</label>
            <input
              type="text"
              {...register("accountHolderName", { required: true })}
              className="w-full p-2 border rounded"
            />
          </div>
          <div>
            <label className="block font-semibold">Balance</label>
            <input
              type="number"
              {...register("balance", { required: true })}
              className="w-full p-2 border rounded"
            />
          </div>
          <div>
            <label className="block font-semibold">Password</label>
            <input
              type="password"
              {...register("password", { required: true })}
              className="w-full p-2 border rounded"
            />
          </div>
          <button type="submit" className="w-full bg-blue-500 text-white p-2 rounded hover:bg-blue-600">
            Create Account
          </button>
        </form>
      </div>
    </div>
  );
}

export default Register;
